package com.example.application.test.sf.newsfproject;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mahel1 on 2015-05-05.
 */

    @SuppressWarnings("rawtypes")
    public class MainClass extends ActivityInstrumentationTestCase2 {
        protected Solo solo;
        private static Class launcherActivityClass;

        static {
            try {
                launcherActivityClass = Class.forName("se.sfbio.mobile.android.SplashActivity");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @SuppressWarnings("unchecked")
        protected MainClass() throws ClassNotFoundException {
            super(launcherActivityClass);
        }

        @Override
        protected void setUp() throws Exception {
            logging("Will initate the solo object.");
            super.setUp();
            logging("Will iniate solo.");
            solo = new Solo(getInstrumentation());
            logging("Will get activity.");
            getActivity();
        }

        @Override
        protected void tearDown() throws Exception {
            super.tearDown();
            solo.finishOpenedActivities();
        }

        protected void logging(final String s) {
            try {
                String filename = "/data/local/tmp/logg.txt";
                FileWriter fw = new FileWriter(filename, true); //the true will append the new data
                fw.write(s + "\r\n");
                fw.close();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
        public void waitForApplicationToStart() {
            logging("Starting application");
            solo.sleep(5000);
            solo.waitForView(android.widget.RelativeLayout.class, 2, 60);
        }
        public void goToTicketsTab() {
            logging("Attempting to click on the ticket tab.");
            solo.clickOnImage(1);
            solo.waitForView(android.widget.ImageView.class, 6, 60);
            assertTrue("Found the tickets tab.", solo.searchText("FILMER I"));
        }




    }

