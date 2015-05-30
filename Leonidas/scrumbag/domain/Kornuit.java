package scrumbag.domain;
 
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
 
public class Kornuit {
    private String name;
    private String id;
    private int ammountOfFeedLikes = 0;
    private int ammountOfStatusLikes = 0;
    private int messagesSent = 0;
    private int ammountOfTags = 0;
    private boolean wasLast = false;
    public int getAmmountOfTags() {
		return ammountOfTags;
	}

	public int getMessagesReceived() {
		return messagesReceived;
	}

	public void setAmmountOfTags(int ammountOfTags) {
		this.ammountOfTags = ammountOfTags;
	}

	public void setMessagesReceived(int messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	private int messagesReceived = 0;
    private Date lastAppointment;
    private float score;
    private float[] scores = { 0f, // TIME
            0f, // FEEDLIKE
            0f // IO message module
    };
    private boolean ignore;
 
    public Kornuit() {
        if (Math.random() > .75) {
            setLastAppointment(randomDate());
        } else {
            setLastAppointment(new Date());
        }
    }
 
    public int daysSinceAppointment() {
        // System.out.println(name + " had appointment at " + lastAppointment.toString());
        long diff = lastAppointment.getTime() - new Date().getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) * -1;
    }
 
    public Date randomDate() {
        long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
        long OFFSET_IN_DAYS = 100;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime() - MILLIS_PER_DAY * Math.round((new Random().nextFloat() * OFFSET_IN_DAYS)));
        return new Date(calendar.getTimeInMillis());
    }
 
    public float getScore() {
        return score;
    }
 
    public float getScore(int index) {
        return scores[index];
    }
 
    public void setScore(int index, float score) {
        scores[index] = score;
    }
 
    public void setScore(float score) {
        this.score = score;
    }
 
    public float[] getScores() {
        return scores;
    }
 
    public void setScores(float[] scores) {
        this.scores = scores;
    }
 
    public String toString() {
        return name + scores[1];
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public Date getLastAppointment() {
        return lastAppointment;
    }
 
    public void setLastAppointment(Date lastAppointment) {
        this.lastAppointment = lastAppointment;
    }
 
    public boolean isIgnored() {
        return ignore;
    }
 
    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

	public int getAmmountOfFeedLikes() {
		return ammountOfFeedLikes;
	}

	public void setAmmountOfFeedLikes(int ammountOfFeedLikes) {
		this.ammountOfFeedLikes = ammountOfFeedLikes;
	}

	public int getAmmountOfStatusLikes() {
		return ammountOfStatusLikes;
	}

	public void setAmmountOfStatusLikes(int ammountOfStatusLikes) {
		this.ammountOfStatusLikes = ammountOfStatusLikes;
	}

	public int getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(int messagesSent) {
		this.messagesSent = messagesSent;
	}

	public boolean isWasLast() {
		return wasLast;
	}

	public void setWasLast(boolean wasLast) {
		this.wasLast = wasLast;
	}
}