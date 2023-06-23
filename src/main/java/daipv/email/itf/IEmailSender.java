package daipv.email.itf;

public interface IEmailSender {
    void send(String to, String body, String topic);

}
