package shayari.world.allinonecaptionapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.Random;

public class insta_adapter extends RecyclerView.Adapter<insta_adapter.ViewHolder> {

    Context context;
    ArrayList<data> list;


    private  int lastPosition =-1;

    public insta_adapter(Context context, ArrayList<data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public insta_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from ( context).inflate ( R.layout.insta_custom_layout,parent,false );

        ViewHolder viewHolder = new ViewHolder ( view );


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull insta_adapter.ViewHolder holder, final int position) {

        holder.text.setText ( list.get ( position ).getText () );
        holder.img.setImageResource ( list.get ( position ).getId ());

        holder.cardView.setOnClickListener ( new View.OnClickListener () {
            String text = list.get ( position ).getText ().toString ();

            @Override
            public void onClick(View v) {

                ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService ( Context.CLIPBOARD_SERVICE );
                ClipData clipData = ClipData.newPlainText ( "text",text );
                clipboardManager.setPrimaryClip ( clipData );

                Toast.makeText ( context, "Caption Copied!!", Toast.LENGTH_SHORT ).show ();
            }
        } );

        setAnimation ( holder.itemView,position );
    }

    protected void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random ().nextInt(1001));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView img;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );

            text = (TextView) itemView.findViewById ( R.id.text_name );
            img = (ImageView) itemView.findViewById ( R.id.img );
            cardView = (CardView) itemView.findViewById ( R.id.cardview );
        }
    }
}