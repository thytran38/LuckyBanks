package com.example.myfirstapp.luckybankonlinesystem;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TransactionActivity extends AppCompatActivity {
    private EditText reciEt, idEt, messageEt, amountEt;
    private Button transBtn;

    private static final String KEY_TRANSACTION_ID = "transactionid";
    private static final String KEY_ACCOUNT_RECEIVER = "receiver";
    private static final String KEY_ACC_SENDER = "sender";
    private static final String KEY_RECEIVER_UID = "receiverid";
    private static final String KEY_SENDER_UID = "senderid";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_TRANSACTION_TIME = "timestamp";
    private static final String KEY_MESSAGE = "message";

    private static final String KEY_CURRENT_BALANCE = "currentBalance";


    private static String receiver = "";
    private static String sender = "";
    private static String receiverID = "";
    private static String senderID = "";
    private static String amount = "";
    private static String timestamp = "";
    private static String message = "";

    private static String UID = "";
    private static String Email = "";


    private static String current_balance = "";

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Boolean existedAccount;
    private String idOfReceiver;
    private String transactionId;

    public TransactionActivity() {
 // DusQGX0wnazH2TMFnkCD
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_a_transaction);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            sender = user.getDisplayName();
            Email = user.getEmail();
            UID = user.getUid();
        }else{

        }

        reciEt = (EditText)findViewById(R.id.etAccnum);
        messageEt = (EditText)findViewById(R.id.etMessage);
        amountEt = (EditText)findViewById(R.id.etAmount);

        idOfReceiver = reciEt.getText().toString();
        receiver = db.collection("users").document(idOfReceiver).get().toString();
        sender = db.collection("users").document().get().toString();
        receiverID = idOfReceiver;
        senderID = "get id from current login session";
        amount = amountEt.getText().toString();
        message = messageEt.getText().toString();
        timestamp = "";

        //transactionId = IdGenerator(receiverID,senderID,timestamp);

//        idOfReceiver = "" ;
//        receiver = "Tran Thi B";
//        sender = "Nguyen Van A";
//        receiverID = "525jj2nf";
//        senderID = "259924hf2h2";
//        amount = "2500";
//        message = "Thanks";
//        timestamp = "03/12/2020 14:16:24";

        transactionId = "2498Jjrw25HH";

        Map<String,Object> transaction = new HashMap<>();
        transaction.put(KEY_TRANSACTION_ID,transactionId);
        transaction.put(KEY_ACC_SENDER,sender);
        transaction.put(KEY_ACCOUNT_RECEIVER,receiver);
        transaction.put(KEY_RECEIVER_UID,receiverID);
        transaction.put(KEY_SENDER_UID,senderID);
        transaction.put(KEY_TRANSACTION_TIME,timestamp);
        transaction.put(KEY_AMOUNT,amount);
        transaction.put(KEY_MESSAGE,message);


        db.collection("transactions").document().getId();
        db.collection("transactions").document(transactionId).set(transaction);
        writeDebt("259924hf2h2");



    }

    public String getTimestamp(){
        return "";
    }

    public void writeDebt(String senderId){

        //current_balance = db.collection("users").document("Xp01Mz36fS2mFnP0Vyqk").collection("accounts").document("R2Fl2DnNRRK5K4jvQcm7").get().toString();
        current_balance = "45000";
//        Double currentBalance = Double.parseDouble(current_balance);
//        Double am = Double.parseDouble(amount);
//        Double result = currentBalance - am;
        Map<String,Object> debt = new HashMap<>();
        debt.put(KEY_CURRENT_BALANCE,"35000");
        debt.put("old balance", "45000");
        db.collection("users").document("abc").collection("accounts").getId();
        db.collection("users").document("abc").collection("accounts").document("newId").set(debt);

        Log.d("SUCCESS","Add debt successfully");
    }

    public void writeEarning(String recipientId){
        current_balance = db.collection("users").document("Xp01Mz36fS2mFnP0Vyqk").collection("accounts").document("R2Fl2DnNRRK5K4jvQcm7").get().toString();
        Double currentBalance = Double.parseDouble(current_balance);
        Double am = Double.parseDouble(amount);
        Double result = currentBalance + am;
        Map<String,Object> debt = new HashMap<>();
        debt.put(KEY_CURRENT_BALANCE,result.toString());
        db.collection("users").document("Xp01Mz36fS2mFnP0Vyqk").collection("accounts").document("R2Fl2DnNRRK5K4jvQcm7").set(debt);
        Log.d("SUCCESS","Add debt successfully");


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);


    }
}
