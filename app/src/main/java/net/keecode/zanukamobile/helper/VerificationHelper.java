package net.keecode.zanukamobile.helper;

/**
 * Created by bteusm on 17.06.2016.
 */
public abstract class VerificationHelper {

    private VerificationHelper(){}

    //TODO: Server-Side Verification

    /**
     * Sends the verification code and the phonenumber to the Zanuka Servers to verify
     * @param verificationCode The verification code
     * @return If the code is valid
     */
    public static boolean verifyCode(String verificationCode){
        if(verificationCode.equals("698431"))
            return true;
        return false;
    }



}
