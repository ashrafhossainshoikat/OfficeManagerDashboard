package services.Email;

/**
 * Main class to send the email with default settings. If default setting is not
 * sufficient, use ServiceSoapProxy class
 */
public class EmailSender extends ServiceSoapProxy {

	public String fromName;
	public String fromEmail;
	private String multiToNameWithEmail;
	public String toName;
	public String[] toNameMulti;
	public String toEmail;
	public String[] toEmailMulti;
	public String mailSubject;
	public String mailBody;
	private boolean isSuccessful;

	public boolean send() {
		if (fromName == null || fromName.isEmpty())
			fromName = fromEmail;
		if (toName == null || toName.isEmpty())
			toName = toEmail;

		try {
			(new Thread() {
				public void run() {
					try {
						sendMailWithName(fromName, fromEmail, toName, toEmail, mailSubject, mailBody);
						isSuccessful = true;
					} catch (java.rmi.RemoteException re) {
						// TODO error log
						re.printStackTrace();
						isSuccessful = false;
					}
				}
			}).start();
		} catch (Exception ex) {
			// TODO error log
			ex.printStackTrace();
			isSuccessful = false;
		}

		return isSuccessful;
	}

	public boolean sendToMulti() {
		multiToNameWithEmail = generateMultiTo(toNameMulti, toEmailMulti);
		if (fromName == null || fromName.isEmpty())
			fromName = fromEmail;

		try {
			(new Thread() {
				public void run() {
					try {
						sendMail(fromName + " <" + fromEmail + ">", multiToNameWithEmail, mailSubject, mailBody);
						isSuccessful = true;
					} catch (java.rmi.RemoteException re) {
						// TODO error log
						isSuccessful = false;
					}
				}
			}).start();
		} catch (Exception ex) {
			// TODO error log
			isSuccessful = false;
		}

		return isSuccessful;
	}

	private static String generateMultiTo(String[] names, String[] emails) {
		String multiTo = "";
		int len = names.length;

		if (len != emails.length)
			return "";

		for (int i = 0; i < len; i++) {
			String e = emails[i];
			String n = names[i];

			if (n != null && e != null && !e.isEmpty()) {
				if (n.isEmpty())
					multiTo = multiTo + e + " <" + e + ">, ";
				else
					multiTo = multiTo + n + " <" + e + ">, ";
			}
		}

		// ** remove last comma
		if (multiTo.endsWith(", "))
			multiTo = multiTo.substring(0, multiTo.length() - 2);

		return multiTo;
	}
}
