package schirmer.nicolas.aula07;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import schirmer.nicolas.aula07.json.User;

public class MainActivity extends AppCompatActivity {

    ImageView avatar, img;
    TextView name;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView) findViewById(R.id.textView);
        avatar = (ImageView) findViewById(R.id.imageView);
        img = (ImageView) findViewById(R.id.imageView2);


        // verifica se tem dados salvos
        database = mFireData.getDatabase();
        reference = database.getReference("results/");

        setUser();


        createNewFragment();
    }


    List<User> users = new ArrayList<>();
    private void setUser(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    users.add(user);
                }

                name.setText(users.get(0).getResults().get(0).getName().getFirst());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void createNewFragment(){
        FragmentQualquer fragment = new FragmentQualquer();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
