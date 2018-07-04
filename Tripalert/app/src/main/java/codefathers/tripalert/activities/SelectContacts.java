package codefathers.tripalert.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import codefathers.tripalert.R;
import codefathers.tripalert.interfaces.NextStepActivity;
import codefathers.tripalert.models.Tracking;
import codefathers.tripalert.models.AppUser;
import codefathers.tripalert.services.DatabaseService;

public class SelectContacts extends AppCompatActivity implements NextStepActivity{

    private Tracking tracking;
    private List<AppUser> contacts;
    private List<AppUser> followers = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contacts);
        getData();
    }
    @Override
    public void onNext(View v) {

        if(tracking.getCreator().getFollowers() != null){

            Intent intent = new Intent(SelectContacts.this,ConfirmTracking.class);
            List <AppUser> userList = new ArrayList<AppUser>();
            userList.add(new AppUser("6948231245"));
            userList.add(new AppUser("6948261245"));
            setFollowers(userList);
            intent.putExtras(getBundle());
            startActivity(intent);
        }else{
            String msg = getString(R.string.cannotProcceedFollowers);
            Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putSerializable("tracking",tracking);
        return bundle;
    }

    @Override
    public void onCancel(View v) {
        startActivity(new Intent(SelectContacts.this,HomeScreen.class));
    }

    @Override
    public void getData() {
        tracking = (Tracking)getIntent().getSerializableExtra("tracking");
    }

    @Override
    public void saveData() {

    }

    @Override
    public void onBackPressed()
    {
        String msg = getString(R.string.cannotProcceedGoBack);
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setFollowers(List<AppUser> followers) {

        this.followers = followers;
    }


    public void gtData(View v) {
        DatabaseService databaseService = new DatabaseService();
        databaseService.readTracksFromDb();
        databaseService.readUsersFromDb();

    }

}
