package nicolas.schirmer.aula04;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


// usar support.v4.app.fragment
public class FragmentPerfil extends Fragment {

    TextView textView;

    // especifica qual layout quer inflar
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup viewGroup, Bundle savedInstance){

        return inflater.inflate(R.layout.fragment_perfil, viewGroup, false);
    }

    // instancia de views
    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        textView = (TextView) view.findViewById(R.id.perfil_nome);
    }

    // nos garante que activity ja foi criada
    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        textView.setText("Ted jr.");
    }

}
