package com.rupeng.view;

import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
//import android.support.v4.content.Loader;    不用这个！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangzhongyu.myapplication.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class FileCategoryFragment extends Fragment {//<泛型>

    RecyclerView recyclerView;
    private List<FileMeta> mDatas = new ArrayList<FileMeta>();
    private  HomeAdapter mHomeAdapter;

    static FileCategoryFragment newInstance(String s){

        FileCategoryFragment f = new FileCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("hello",s);
        f.setArguments(bundle);

        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card,container,false);

        initData();

        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        GridLayoutManager mgr = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mgr);

        mHomeAdapter = new HomeAdapter();
        recyclerView.setAdapter(mHomeAdapter);

        return  view;
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.rv_item, parent,
                    false));
            return holder;
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position)
        {
            String fileType = mDatas.get(position).fileType == null ?"":mDatas.get(position).fileType;
            final String filePath = mDatas.get(position).filePath == null ?"":mDatas.get(position).filePath;
            holder.tv.setText(fileType + filePath);
            if(mDatas.get(position).fileIcon == 0){
                holder.iv.setVisibility(View.GONE);
                holder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openFile(new File(filePath));
                    }
                });
            }else{
                holder.iv.setBackgroundResource(mDatas.get(position).fileIcon);
                holder.iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //   第一个参数设置为hash值 or  在ondestroy的时候干掉这个loader,否则多次启动这个fragment不会触犯数据库查询
                        String fileType = mDatas.get(position).fileType;
                        loadMutliData(position,fileType);
                    }
                });
           }
        }

        private  void loadMutliData(int id,final String fileType){
            android.support.v4.content.Loader<Cursor>  loader =
            getLoaderManager().initLoader(id, null, new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public android.support.v4.content.Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
                    if(fileType.equals("音频")){
                                return new CursorLoader(getActivity(),MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
                    }else if(fileType.equals("视频")){
                        return new CursorLoader(getActivity(),MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null,
                                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
                    }else if(fileType.equals("安装包")){
                        return new CursorLoader(getActivity(),MediaStore.Files.getContentUri("external"), null, buildSelectionByCategory(FileCategory.Apk), null,
                                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
                    }else if(fileType.equals("压缩文件")){
                        return new CursorLoader(getActivity(),MediaStore.Files.getContentUri("external"), null, buildSelectionByCategory(FileCategory.Zip), null,
                                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
                    }else if(fileType.equals("图片")){
                        return new CursorLoader(getActivity(),MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,
                                null);
                    }else if(fileType.equals("文档")){
                        return new CursorLoader(getActivity(),MediaStore.Files.getContentUri("external"), null, buildDocSelection(), null,
                                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
                    }
                    return  null;
                }

                @Override
                public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor cursor) {
                    mDatas.clear();
                    while (cursor.moveToNext()) {
                        FileMeta file = new FileMeta();
                        file.filePath = cursor.getString(cursor.getColumnIndex("_data"));
                        file.fileName = file.filePath.split("/")[file.filePath.split("/").length - 1];

                        mDatas.add(file);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mHomeAdapter.notifyDataSetChanged();
                }

                @Override
                public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

                }

            });

            loader.forceLoad();
        }
        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;
            ImageView iv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.name);
                iv = (ImageView) view.findViewById(R.id.icon);

            }
        }
    }

    public void showCategoryView(){
        initData();

        GridLayoutManager mgr = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mgr);
        mHomeAdapter.notifyDataSetChanged();
    }

    protected void initData()
    {
        mDatas.clear();
        FileMeta m1 = new FileMeta();
        m1.fileType = "图片";
        m1.fileIcon = R.drawable.photo;

        FileMeta m2 = new FileMeta();
        m2.fileType = "音频";
        m2.fileIcon = R.drawable.music;

        FileMeta m3 = new FileMeta();
        m3.fileType = "视频";
        m3.fileIcon = R.drawable.video;

        FileMeta m4 = new FileMeta();
        m4.fileType = "文档";
        m4.fileIcon = R.drawable.doc;

        FileMeta m5 = new FileMeta();
        m5.fileType = "压缩包";
        m5.fileIcon = R.drawable.zip;

        FileMeta m6 = new FileMeta();
        m6.fileType = "安装包";
        m6.fileIcon = R.drawable.apk;

        mDatas.add(m1);
        mDatas.add(m2);
        mDatas.add(m3);
        mDatas.add(m4);
        mDatas.add(m5);
        mDatas.add(m6);
    }


    protected void openFile(File aFile)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        File file = new File(aFile.getAbsolutePath());
        String fileName = file.getName();
        if (checkEndsWithInStringArray(fileName, getResources().getStringArray(R.array.fileEndingImage)))
        {
            intent.setDataAndType(Uri.fromFile(file), "image/*");
        }
        else if (checkEndsWithInStringArray(fileName, getResources().getStringArray(R.array.fileEndingAudio)))
        {
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
        }
        else if (checkEndsWithInStringArray(fileName, getResources().getStringArray(R.array.fileEndingVideo)))
        {
            intent.setDataAndType(Uri.fromFile(file), "video/*");
        }else if(checkEndsWithInStringArray(fileName, getResources().getStringArray(R.array.fileEndingWebText))){
            intent.setDataAndType(Uri.fromFile(file), "text/plain");

        } else if(checkEndsWithInStringArray(fileName,getResources().getStringArray(R.array.fileEndingApkt))){
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }
    private boolean checkEndsWithInStringArray(String checkItsEnd, String[] fileEndings)
    {
        for(String aEnd : fileEndings)
        {
            if(checkItsEnd.endsWith(aEnd))
                return true;
        }
        return false;
    }



    private String buildDocSelection() {
        StringBuilder selection = new StringBuilder();
        Iterator<String> iter = sDocMimeTypesSet.iterator();
        while(iter.hasNext()) {
            selection.append("(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='" + iter.next() + "') OR ");
        }
        return  selection.substring(0, selection.lastIndexOf(")") + 1);
    }


    private String buildSelectionByCategory(FileCategory cat) {
        String selection = null;
        switch (cat) {
            case Theme:
                selection = MediaStore.Files.FileColumns.DATA + " LIKE '%.mtz'";
                break;
            case Doc:
                selection = buildDocSelection();
                break;
            case Zip:
                selection = "(" + MediaStore.Files.FileColumns.MIME_TYPE + " == '" + sZipFileMimeType + "')";
                break;
            case Apk:
                selection = MediaStore.Files.FileColumns.DATA + " LIKE '%.apk'";
                break;
            default:
                selection = null;
        }
        return selection;
    }

    public enum FileCategory {
        All, Music, Video, Picture, Theme, Doc, Zip, Apk, Custom, Other, Favorite
    }

    public static String sZipFileMimeType = "application/zip";

    public static HashSet<String> sDocMimeTypesSet = new HashSet<String>() {
        {
            add("text/plain");
            add("text/plain");
            add("application/pdf");
            add("application/msword");
            add("application/vnd.ms-excel");
            add("application/vnd.ms-excel");
        }
    };
}