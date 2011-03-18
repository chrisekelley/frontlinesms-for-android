/**
 * 
 */
package net.frontlinesms.android.util.sms;

import android.telephony.SmsMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author aga
 */
public class WholeSmsMessage {

	/** ordered list of parts comprising this message. */
	private final List<SmsMessage> parts;
	
	WholeSmsMessage(SmsMessage[] parts) {
		this.parts = Collections.unmodifiableList(Arrays.asList(parts));
	}

	public String getMessageBody() {
		StringBuilder bob = new StringBuilder();
		for(SmsMessage m : parts) {
			bob.append(m.getMessageBody());
		}
		return bob.toString();
	}

	public String getOriginatingAddress() {
		return parts.get(0).getOriginatingAddress();
	}
}
