package nicolas.schirmer.aula04;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// precisa ser o support.v4.app
public class FragmentLista extends Fragment{

    ListView listView;

    // especifica qual layout quer inflar
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup viewGroup, Bundle savedInstance){

        return inflater.inflate(R.layout.fragment_lista, viewGroup, false);
    }

    // instancia de views
    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        listView = (ListView) view.findViewById(R.id.listView);
    }

    // nos garante que activity ja foi criada
    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("bla", R.drawable.avatar));
        pessoas.add(new Pessoa("ble", R.drawable.avatar));
        pessoas.add(new Pessoa("bli", R.drawable.avatar));
        pessoas.add(new Pessoa("blo", R.drawable.avatar));

        // colocar codigo executavel
        listView.setAdapter(new CustomListAdapter(pessoas, getContext()));
    }
}
