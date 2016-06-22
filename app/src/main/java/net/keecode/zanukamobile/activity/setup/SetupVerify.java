package net.keecode.zanukamobile.activity.setup;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.keecode.zanukamobile.R;
import net.keecode.zanukamobile.element.NoSwipeViewPager;
import net.keecode.zanukamobile.helper.VerificationHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetupVerify extends SetupFragment {

    private static String verifyCode = "";
    private static Fragment c;


    private Button continueButton;
    private static EditText verifyCodeText;
    public SetupVerify() {
        fragmentId = R.layout.fragment_setup_verify;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup_verify, container, false);
    }

    @Override
    public void start(final Fragment c, final NoSwipeViewPager viewPager) {
        SetupVerify.c = c;
        verifyCodeText = (EditText) c.getActivity().findViewById(R.id.setup_verify_code);
        continueButton = (Button) c.getActivity().findViewById(R.id.setup_verify_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetupWaiting.setStatusText(c.getActivity().getString(R.string.setup_waiting_status_check_code));
                SetupWaiting.setNextScreen(4);
                viewPager.setCurrentItem(3, true);
                final String enteredCode = verifyCodeText.getText().toString();
                SetupWaiting.setAsyncTask(new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object... objects) {
                        try {
                            Thread.sleep(2000);
                            return VerificationHelper.verifyCode(enteredCode);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        if((boolean)o){
                            SetupWaiting.setNextScreen(4);
                        }else{
                            SetupWaiting.setNextScreen(2);
                        }
                        super.onPostExecute(o);
                    }
                });
            }
        });
    }

    @Override
    public void resume(Fragment c) {

    }

    @Override
    public void pause(Fragment c) {

    }

    @Override
    public void stop(Fragment c) {

    }

    public static String getVerifyCode() {
        return verifyCode;
    }

    public static void setVerifyCode(String verifyCode) {
        SetupVerify.verifyCode = verifyCode;
        verifyCodeText.setText(verifyCode);
    }

    private static void beginVerify(){

    }


}
