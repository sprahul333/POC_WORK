package framework.constants;

public enum StatusCodes {

    SUCCESS(200),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503),;

    private int statusCode;

    StatusCodes(int code) {
        this.statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }




}
