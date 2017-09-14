package nicolas.schirmer.aula04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomListAdapter  extends BaseAdapter {

    List<Pessoa> pessoas = new ArrayList<>();
    Context context;

    public CustomListAdapter(List<Pessoa> pessoas, Context context){
        this.pessoas = pessoas;
        this.context = context;
    }

    // itens da view que precisam ser instanciados
    private class ViewHolder {
        TextView nome;
        CircleImageView foto;

        public ViewHolder(View view){
            nome = (TextView) view.findViewById(R.id.item_amigos_nome);
            foto = (CircleImageView) view.findViewById(R.id.item_amigos_avatar);
        }
    }

    @Override
    public int getCount(){
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // verifica se ainda tá na memória
        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_amigos, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Pessoa pessoa = pessoas.get(position);

        viewHolder.nome.setText(pessoa.getNome());
        viewHolder.foto.setImageResource(pessoa.getImgRes());

        return convertView;
    }


}
