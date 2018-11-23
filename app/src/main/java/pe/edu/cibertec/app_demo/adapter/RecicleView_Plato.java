package pe.edu.cibertec.app_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import pe.edu.cibertec.app_demo.R;
import pe.edu.cibertec.app_demo.bean.PlatosBean;

public class RecicleView_Plato extends RecyclerView.Adapter<RecicleView_Plato.viewHolder_Plato> {

    private List<PlatosBean> arreglo;
    private int viewModel;
    private Context context;

    public RecicleView_Plato(List<PlatosBean> arreglo,int viewModel,Context context) {
        this.arreglo = arreglo;
        this.viewModel = viewModel;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder_Plato onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewModel,parent,false);
        return new viewHolder_Plato(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder_Plato holder, int position) {
        PlatosBean plato = arreglo.get(position);
        holder.titulo_card.setText(plato.getTitulo());
        holder.tiempo_card.setText(plato.getTiempo());
        holder.precio_card.setText(plato.getPrecio());
        Picasso.get().load(plato.getUrl()).into(holder.imagen_card);
    }

    @Override
    public int getItemCount() {
        return arreglo.size();
    }

    public class viewHolder_Plato extends RecyclerView.ViewHolder{

        private ImageView imagen_card;
        private TextView titulo_card;
        private TextView tiempo_card;
        private TextView precio_card;

        public viewHolder_Plato(View itemView) {
            super(itemView);
            imagen_card = itemView.findViewById(R.id.imagen_card);
            titulo_card = itemView.findViewById(R.id.titulo_card);
            tiempo_card = itemView.findViewById(R.id.tiempo_card);
            precio_card = itemView.findViewById(R.id.precio_card);
        }
    }
}
