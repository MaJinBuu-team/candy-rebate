package cn.com.mjb.candyrebateweb.service.mail;

import java.util.Map;
import java.util.Set;

public interface EmailService {

    /**
     * Send simple mail int.
     *
     * @param subject the subject
     * @param text    the text
     * @param to      the to
     * @return the int
     */
    int sendSimpleMail(String subject, String text, Set<String> to);

    /**
     * Send template mail int.
     *
     * @param subject the subject
     * @param text    the text
     * @param to      the to
     * @return the int
     */
    int sendTemplateMail(String subject, String text, Set<String> to);

    /**
     * Send template mail int.
     *
     * @param model            the model
     * @param templateLocation the template location
     * @param subject          the subject
     * @param to               the to
     * @return the int
     */
    int sendTemplateMail(Map<String, Object> model, String templateLocation, String subject, Set<String> to);

}
