package com.company;

import java.util.Vector;

//-----------------------------------------------------------------------------


interface BewilderOperation {
    public String operate(String str);
}

//-----------------------------------------------------------------------------

class InstructionSets {
    public final static String ValidationStr="TEST";

    public static String[] OutStrings = {
            "Hsfbu!xpsl!gjyjoh!uif!dpnqjmf!fsspst\"!!Lffq!efcvhhjoh!up!vodpwfs!uif!sfnbjojoh!nfttbhft\"",
            "Dpohsbuvmbujpot\"!!Xf!bsf!uisjmmfe!uibu!zpv!gjyfe!uijt!qpps!mjuumf!bqq(t!cvht!up!sfdfjwf!uijt!nfttbhf/",
            "Qmfbtf!gffm!gsff!up!bee!boz!puifs!jnqspwfnfout/",
            "Podf!dpnqmfuf-!qmfbtf!fnbjm!zpvs!dpef!up!jogpApqfoqbuiqspevdut/dpn!boe!dbmm!vt!bu!521/9:8/1517!up!tdifevmf!b!dbmm!xjui!uif!ufbn/!",
            "Xf!bsf!mppljoh!gpsxbse!up!ubmljoh!xjui!zpv!bcpvu!tpnf!bnb{joh!dbsffs!pqqpsuvojujft\"!"
    } ;

    // Java Lambda functions- Neat... but could refactoring be applied here?
    public static BewilderOperation ConfuseOperation = (String str) -> {
        StringBuffer sb = new StringBuffer();
        char chars[] = str.toCharArray();
        for (char c : chars) {
            int ci = (int)c;
            char cn = (char) (ci+1);
            sb.append((char) cn);
        }
        return sb.toString();
    };

    public static BewilderOperation ClarifyOperation = (String str) -> {
        StringBuffer sb = new StringBuffer();
        char chars[] = str.toCharArray();
        for (char c : chars) {
            int ci = (int) c;
            sb.append((char) (ci-1) );
        }
        return sb.toString();
    };

    public static String GetString(BewilderOperation bewilderOperation, String str) {
        return bewilderOperation.operate(str);
    }

}

//-----------------------------------------------------------------------------

public class Puzzle implements Runnable {
    private Vector<String> logMsgVec = new Vector<>();
    private boolean running = false;
    private int messagesToShare = logMsgVec.size();
    private Thread newThread;

    public Puzzle() {
        running = true;
        newThread = new Thread(this);
        newThread.start();
    }

    public void solvePuzzle() {
        //first let's just try to check if our function lambdas do what we think they do
        String confusedTestStr = InstructionSets.GetString( InstructionSets.ConfuseOperation, InstructionSets.ValidationStr);
        String clarifiedTestStr = InstructionSets.GetString( InstructionSets.ClarifyOperation, confusedTestStr);

        if (!clarifiedTestStr.equals(InstructionSets.ValidationStr)) {
            log("Oh no! Could the Bewilder Operations be malfunctioning?");
        } else
        {
            //let's see what the instructions have to say...
            for (String str : InstructionSets.OutStrings) {
                log(InstructionSets.GetString(InstructionSets.ClarifyOperation, str));
            }
        }
        try {
            newThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }

    private void log(String msg) {
        logMsgVec.add(msg);
    }

    public void run() {
        while (running) {
            if (logMsgVec.size()>0) {
                String logMsg = logMsgVec.firstElement();
                logMsgVec.remove(logMsg);
                System.out.println("\n-> " + logMsg);
            }
            try {
                if (logMsgVec.size()>1) {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        System.out.println("When working, the log messages will reveal next steps...");
        Puzzle puzzle = new Puzzle();
        puzzle.solvePuzzle();
    }


}

//--------------------------------------------------------------
