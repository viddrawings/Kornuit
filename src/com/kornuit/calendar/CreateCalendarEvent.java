package com.kornuit.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CreateCalendarEvent {
    /** Application name. */
    private final String APPLICATION_NAME =
        "Kornuit";

    /** Directory to store user credentials. */
    private final static java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/kornuit");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart. */
    private final List<String> SCOPES =
        Arrays.asList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            CreateCalendarEvent.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver.Builder().setPort(5124).build()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     */
    public com.google.api.services.calendar.Calendar
        getCalendarService() throws IOException {
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public boolean createEvent(Afspraak a) throws IOException {
    	
        try {
        	com.google.api.services.calendar.Calendar service =
                    getCalendarService();
                
            Event newEvent = new Event();
            newEvent.setSummary("KORNUIT: Afspraak met " + a.getFacebookVriendNaam() + ", de activiteit is: " + a.getActiviteit());
            newEvent.setLocation(a.getLocatie());

            ArrayList<EventAttendee> attendees = new ArrayList<EventAttendee>();
            String email = (a.getFacebookVriendNaam() + "@gmail.com").replaceAll("\\s","");
            attendees.add(new EventAttendee().setDisplayName(a.getFacebookVriendNaam()).setEmail(email));
            newEvent.setAttendees(attendees);

            Date startDate = new Date(a.getDatumTijd().getTime());
            Date endDate = new Date(startDate.getTime() + (3600000 * 3));
            DateTime dasStart = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
            newEvent.setStart(new EventDateTime().setDateTime(dasStart));
            DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
            newEvent.setEnd(new EventDateTime().setDateTime(end));

            // Insert the new event
			service.events().insert("primary", newEvent).execute();
            
            return true;
        } catch (Exception e) {
        	System.err.println(e);
        	return false;
        }
    	
    }

}