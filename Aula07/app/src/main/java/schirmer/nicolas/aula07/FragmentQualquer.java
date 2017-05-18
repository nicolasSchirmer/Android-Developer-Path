package schirmer.nicolas.aula07;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentQualquer extends Fragment {

    TextView name;
    ImageView photo;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_view, container, false);

        name = (TextView) view.findViewById(R.id.textName);
        photo = (ImageView) view.findViewById(R.id.imagePhoto);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle saveState){
        super.onActivityCreated(saveState);

        name.setText("bla");
    }

}
