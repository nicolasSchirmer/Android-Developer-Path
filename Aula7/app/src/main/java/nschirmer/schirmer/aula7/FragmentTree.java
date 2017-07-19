package nschirmer.schirmer.aula7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentTree extends Fragment {

    Button button;

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup parent, Bundle saveState){

        View view = layoutInflater.inflate(R.layout.fragment_tree, parent, false);

        button = (Button) view.findViewById(R.id.button3);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle saveInstance){
        super.onActivityCreated(saveInstance);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), (getResources().getString(R.string.toast)), Toast.LENGTH_LONG).show();
            }
        });
    }



}
