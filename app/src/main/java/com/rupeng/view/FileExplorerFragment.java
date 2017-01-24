package com.rupeng.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangzhongyu.myapplication.R;

import java.io.File;
import java.io.IOException;


public class  FileExplorerFragment extends Fragment {

    private GridView gv =null;
    private  File[]  filelist = null ;
    private GridViewAdapter gridViewAdapter = null;

    private ImageButton ivNewFile = null;
    private  ImageButton ivPasteFile = null;
    private String currentPath;
    private TextView tvPath;
    private ImageButton btnBack;

    static FileExplorerFragment newInstance(String s){

        FileExplorerFragment f = new FileExplorerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("arguments",s);
        f.setArguments(bundle);

        return f;
    }

    //返回上一级目录
    private  String getPreDirectory(String path){
        String[] p = path.split("/");
        String dst="";
        for(int i=0;i<p.length-1;i++){
            dst += p[i]+"/";
        }
        return dst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_app_grid,container,false);

        filelist = Environment.getExternalStorageDirectory().listFiles();
        tvPath = (TextView) view.findViewById(R.id.tv_file_path);
        btnBack = (ImageButton) view.findViewById(R.id.button_back);
        currentPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        btnBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                currentPath = getPreDirectory(currentPath);
                tvPath.setText(currentPath);
                reLoad();
            }
        });
        tvPath.setText(currentPath);
        gv = (GridView) view.findViewById(R.id.gv_apps);
        gridViewAdapter = new GridViewAdapter(getActivity(),filelist);
        gv.setAdapter(gridViewAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                if(filelist[position].isDirectory()){
                    currentPath = filelist[position].getAbsolutePath()+"/" ;
                    tvPath.setText(currentPath);
                    reLoad();
                }else{
                    openFile(filelist[position]);
                }

            }
        });
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                showListDialog(filelist[position]);
                return true;
            }

        });

        ivNewFile = (ImageButton) view.findViewById(R.id.ib_new_file);
        ivNewFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        showNewFileDlg(FileManagerUtil.NEW_DIRECTORY);
            }
        });

        ivPasteFile = (ImageButton) view.findViewById(R.id.ib_paste_file);
        ivPasteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newFileName = FileManagerUtil.COPY_FILE_PATH.split("/")[FileManagerUtil.COPY_FILE_PATH.split("/").length-1];
                FileManagerUtil.copyFile(FileManagerUtil.COPY_FILE_PATH,currentPath + newFileName );
                reLoad();
            }
        });
        return  view;
    }

    private  void reLoad(){
        filelist = new File(currentPath).listFiles();
        gridViewAdapter = new GridViewAdapter(getActivity(),filelist);
        gv.setAdapter(gridViewAdapter);//这个地方是不是用notify更好些？如果用notify 现在代码有什么问题吗？
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

    public class GridViewAdapter extends BaseAdapter {
        File[] files = null;
        LayoutInflater inflater;
        public GridViewAdapter(Context context, File[] filelist) {
            inflater = LayoutInflater.from(context);
            files = filelist;
        }

        @Override
        public int getCount() {
            return files.length;
        }

        @Override
        public Object getItem(int position) {
            return files[position];
        }

        @Override
        public long getItemId(int position) {
            return  position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.gv_item, null);
            TextView tv = (TextView) view.findViewById(R.id.gv_item_appname);
            ImageView iv = (ImageView) view.findViewById(R.id.gv_item_icon);

            File file = (File) this.getItem(position);
            tv.setText(file.getName());
            if(file.isFile()){
                iv.setImageResource(R.drawable.text);
            }
            if(file.isDirectory()){
                iv.setImageResource(R.drawable.folder);
            }
            return view;
        }

    }

    void showListDialog(final File file){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("选择操作");

        final String[] items = {"重命名","删除","复制","剪切"};

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which)
                {
                    case 0:
                    {
                        showRenameDlg(file);
                    }
                    break;
                    case 1:
                    {
                        file.delete();
                        reLoad();
                    }
                    break;
                    case 2:
                    {
                        FileManagerUtil.COPY_FILE_PATH = file.getAbsolutePath();
                    }
                    break;
                    case 3:
                    {
                        FileManagerUtil.CUT_FILE_PAHT = file.getAbsolutePath();
                    }
                    break;
                }

            }

        });
        AlertDialog ad = builder.create();

        ad.show();

    }
    private void showRenameDlg(final File file) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("重命名");

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View v = inflater.inflate(R.layout.renamedialog, null);
        final EditText et_newName =  (EditText) v.findViewById(R.id.NEW_NAME);

        builder.setView(v);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!et_newName.getText().toString().contains("/")){

                    if(file.renameTo(new File(file.getParent()+"/"+et_newName.getText().toString()))){
                        reLoad();
                        Toast.makeText(getContext(), "重命名成功", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }  );
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }  );
        AlertDialog ad = builder.create();

        ad.show();

    }
    private  void showNewFileDlg(final int FLAG) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("创建文件");
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.renamedialog, null);
        final EditText  et_newName =  (EditText) v.findViewById(R.id.NEW_NAME);
        builder.setView(v);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String filename =et_newName.getText().toString() ;
                if((!FileManagerUtil.isAlreadyExgit(filename, filelist))&&(FileManagerUtil.checkFileName(filename ))){
                    File  newFile = new File(currentPath + File.separatorChar+et_newName.getText().toString());

                    if(FLAG == FileManagerUtil.NEW_DIRECTORY){
                        newFile.mkdir();
                    }
                    else{
                        try {
                            newFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }}
                }
                reLoad();
            }
        }  );
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }  );
        AlertDialog ad = builder.create();
        ad.show();
    }
}