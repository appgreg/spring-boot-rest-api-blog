package springboot.rest.api.blog.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timeStamp;
    private Object message;
    private String details;

    public ErrorDetails(Date timeStamp, Object message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getDetails() {
        return details;
    }

    public Object getMessage() {
        return message;
    }
}
