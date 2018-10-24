package myfab.wildcardenter.com.imager;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
public class ItemRecyclerAdapter extends ShimmerRecyclerView.Adapter<ItemRecyclerAdapter.itemRecyclerViewHolder> {
    private Context mContext;
    private ArrayList<ItemViewModel> mItemList;

    public ItemRecyclerAdapter(Context context,ArrayList<ItemViewModel> arrayList){
        mContext=context;
        mItemList =arrayList;
    }

    @NonNull
    @Override
    public itemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false);
        return new itemRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemRecyclerViewHolder holder, final int position) {
        ItemViewModel currentItem=mItemList.get(position);
        String profile_img_url=currentItem.getProfile_img_url();
        String user_name=currentItem.getUser_name();
        String img_url=currentItem.getImg_url();
        int mlike=currentItem.getMlike();
        int mViews=currentItem.getmViews();
        int mDownloads=currentItem.getmDownloads();
        if (profile_img_url.isEmpty()){
            Picasso.with(mContext).load(R.drawable.splogo).centerCrop().fit()
                    .into(holder.profile_img);
        }else{
        Picasso.with(mContext).load(profile_img_url).centerCrop().fit()
                .into(holder.profile_img);}
        holder.mTextView_user.setText(user_name);
        if (img_url.isEmpty()){
            Picasso.with(mContext).load(R.drawable.splogo)
                    .into(holder.profile_img);
        }else {
            Picasso.with(mContext).load(img_url).centerInside().resize(700, 340)
                    .into(holder.item_img_view);
        }

        holder.mlikes.setText(String.valueOf(mlike));
        holder.mViews.setText(String.valueOf(mViews));
        holder.mDownloads.setText(String.valueOf(mDownloads));
        holder.download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DownloadManager downloadManager= (DownloadManager) mContext.getSystemService(mContext.DOWNLOAD_SERVICE);
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(mItemList.get(position).getLarge_img_url()));
                request.setTitle(mItemList.get(position).getLarge_img_url()).setDescription("Downloading")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalFilesDir(mContext,"Images","ImageR image");
                downloadManager.enqueue(request);
                Toasty.normal(mContext,mItemList.get(position).getUser_name(),Toast.LENGTH_SHORT);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class itemRecyclerViewHolder extends ShimmerRecyclerView.ViewHolder{
        public ImageView profile_img;
        public TextView mTextView_user;
        public ImageView item_img_view;
        public TextView mlikes;
        public TextView mViews;
        public TextView mDownloads;
        public ImageButton download_btn;

        public itemRecyclerViewHolder(View itemView) {
            super(itemView);
            profile_img=itemView.findViewById(R.id.profile_image);
            mTextView_user=itemView.findViewById(R.id.user_name);
            item_img_view=itemView.findViewById(R.id.item_img);
            mlikes=itemView.findViewById(R.id.item_likes);
            mViews=itemView.findViewById(R.id.itemViews);
            mDownloads=itemView.findViewById(R.id.itemDownloads);
            download_btn=itemView.findViewById(R.id.downloadbtn);
        }
    }
}
