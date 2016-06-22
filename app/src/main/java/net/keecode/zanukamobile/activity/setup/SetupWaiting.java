package net.keecode.zanukamobile.activity.setup;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.keecode.zanukamobile.R;
import net.keecode.zanukamobile.element.NoSwipeViewPager;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetupWaiting extends SetupFragment {

    private static String statusText;
    private static Fragment fragment;
    private static NoSwipeViewPager viewPager;
    private static AsyncTask<Object, Object, Object> asyncTask;
    private static int nextScreen = 0;


    public SetupWaiting() {
        fragmentId = R.layout.fragment_setup_waiting;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup_waiting, container, false);
    }

    @Override
    public void start(Fragment c, final NoSwipeViewPager viewPager) {
        fragment = c;
        this.viewPager = viewPager;

        System.out.println(statusText);
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

    public static String getStatusText() {
        return statusText;
    }

    public static void setStatusText(String statusText) {
        SetupWaiting.statusText = statusText;

        TextView status = (TextView) fragment.getActivity().findViewById(R.id.setup_waiting_status);
        status.setText(statusText);
    }

    public static AsyncTask<Object, Object, Object> getAsyncTask() {
        return asyncTask;
    }

    public static void setAsyncTask(final AsyncTask<Object, Object, Object> asyncTask, Object... params) {
        SetupWaiting.asyncTask = asyncTask;
        asyncTask.execute(params);
        new AsyncTask<Object, Object, Object>(){

            @Override
            protected Object doInBackground(Object... objects) {
                try {
                    asyncTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                System.out.println("Opening:" + getNextScreen());
                viewPager.setCurrentItem(getNextScreen(), true);
            }
        }.execute();
    }

    public static int getNextScreen() {
        return nextScreen;
    }

    public static void setNextScreen(int nextScreen) {
        SetupWaiting.nextScreen = nextScreen;
    }
}
