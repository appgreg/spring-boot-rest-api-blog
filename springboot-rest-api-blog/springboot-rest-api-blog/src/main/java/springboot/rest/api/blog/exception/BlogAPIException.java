package springboot.rest.api.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

    private HttpStatus status;


    private String messageDetails;


    public BlogAPIException(HttpStatus status, String messageDetails) {
        this.status = status;
        this.messageDetails = messageDetails;
    }

    public BlogAPIException(String message, HttpStatus status, String messageDetails) {
        super(message);
        this.status = status;
        this.messageDetails = messageDetails;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(String messageDetails) {
        this.messageDetails = messageDetails;
    }

}
