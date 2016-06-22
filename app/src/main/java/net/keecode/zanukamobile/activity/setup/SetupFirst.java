package net.keecode.zanukamobile.activity.setup;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.keecode.zanukamobile.R;
import net.keecode.zanukamobile.element.NoSwipeViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetupFirst extends SetupFragment {

    private Button continueButton;
    private EditText phoneNumberEditText;
    private View rootView;


    public SetupFirst() {
        fragmentId = R.layout.fragment_setup_first;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup_first, container, false);
    }

    @Override
    public void start(final Fragment c, final NoSwipeViewPager viewPager) {
        continueButton = (Button) c.getActivity().findViewById(R.id.setu_first_continue);
        phoneNumberEditText = (EditText) c.getActivity().findViewById(R.id.setup_first_number);
        rootView = c.getActivity().findViewById(R.id.setup_first_layout);


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(!validCellPhone(phoneNumberEditText.getText().toString())){
                    showSnackbar("Du hast keine gültige Nummer angegeben!", rootView);
                    return;
                }
                if(!phoneNumberEditText.getText().toString().startsWith("+41")) {
                    showSnackbar("Die Nummer muss mit +41 beginnen", rootView);
                    return;
                }else
                    phoneNumber = phoneNumberEditText.getText().toString();
                SetupWaiting.setStatusText(c.getActivity().getString(R.string.setup_waiting_status_send_code));
                SetupWaiting.setNextScreen(2);
                viewPager.setCurrentItem(1, true);
                SetupWaiting.setAsyncTask(new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object... objects) {
                        try {
                            Thread.sleep(2000);
                            SmsManager sms = SmsManager.getDefault();
                            sms.sendTextMessage(phoneNumber, null, "698431", null, null);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
            }
        });
    }

    public boolean validCellPhone(String number)
    {
        return PhoneNumberUtils.isGlobalPhoneNumber(number);
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
}
