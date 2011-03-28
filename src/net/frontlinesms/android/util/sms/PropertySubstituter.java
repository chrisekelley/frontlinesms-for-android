/**
 * 
 */
package net.frontlinesms.android.util.sms;

import android.content.ContentResolver;
import android.content.Context;
import net.frontlinesms.android.model.PIMService;
import net.frontlinesms.android.model.model.KeywordAction;

/**
 * @author aga
 *
 */
public class PropertySubstituter {
	public static final String KEY_ORIGINAL_MESSAGE = "${msgBody}";
	public static final String KEY_SENDER_PHONENUMBER = "${senderNum}";
	public static final String KEY_SENDER_NAME = "${senderName}";
	public static final String KEY_DESTINATION_PHONENUMBER = "${recipientNum}";
	public static final String KEY_DESTINATION_NAME = "${recipientName}";

    private final Context mContext;

	public PropertySubstituter(Context context) {
		this.mContext = context;
	}

	/** Substitute properties of the message into the reply text */
	public String substitute(KeywordAction action, WholeSmsMessage message, String destinationAddress, String subText) {
		if(subText.contains(KEY_SENDER_NAME)) {
			String contactName = PIMService.getContactNameByPhoneNumber(mContext, message.getOriginatingAddress());
			if(contactName != null) {
				subText = subText.replace(KEY_SENDER_NAME, contactName);
			}
		}
		
		if(subText.contains(KEY_DESTINATION_NAME)) {
			String contactName = PIMService.getContactNameByPhoneNumber(mContext, destinationAddress);
			if(contactName != null) {
				subText = subText.replace(KEY_DESTINATION_NAME, contactName);
			}
		}
		
		return subText
				.replace(KEY_DESTINATION_PHONENUMBER, destinationAddress)
				.replace(KEY_SENDER_PHONENUMBER, message.getOriginatingAddress())
				.replace(KEY_ORIGINAL_MESSAGE, message.getMessageBody()) // always substitute message body last to avoid injection
				;
	}

}
