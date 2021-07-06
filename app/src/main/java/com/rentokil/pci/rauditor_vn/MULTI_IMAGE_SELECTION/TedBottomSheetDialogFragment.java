package com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.gun0912.tedonactivityresult.TedOnActivityResult;
import com.gun0912.tedonactivityresult.listener.OnActivityResultListener;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.Photo_Editor.EditImageActivity;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.Photo_Editor.EmojiBSFragment;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.Photo_Editor.PropertiesBSFragment;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.Photo_Editor.StickerBSFragment;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.Photo_Editor.filters.FilterListener;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.Photo_Editor.filters.FilterViewAdapter;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.Photo_Editor.tools.EditingToolsAdapter;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.Photo_Editor.tools.ToolType;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.adapter.GalleryAdapter;
import com.rentokil.pci.rauditor_vn.MULTI_IMAGE_SELECTION.util.RealPathUtil;
import com.rentokil.pci.rauditor_vn.R;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import ja.burhanrashid52.photoeditor.OnPhotoEditorListener;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.PhotoFilter;
import ja.burhanrashid52.photoeditor.SaveSettings;
import ja.burhanrashid52.photoeditor.ViewType;

public class TedBottomSheetDialogFragment extends BottomSheetDialogFragment  implements OnPhotoEditorListener,
        View.OnClickListener,
        PropertiesBSFragment.Properties,
        EmojiBSFragment.EmojiListener,
        StickerBSFragment.StickerListener, EditingToolsAdapter.OnItemSelected, FilterListener {
    Dialog dialog;
    Uri uri_selected;
    ImageView img_bitmap,img_send;
    private android.app.AlertDialog pd;
    String Str_IMAGE_1_URL="";
    private static final String EXTRA_CAMERA_IMAGE_URI = "camera_image_uri";
    private static final String EXTRA_CAMERA_SELECTED_IMAGE_URI = "camera_selected_image_uri";
    public BaseBuilder builder;
    private GalleryAdapter imageGalleryAdapter;
    private View view_title_container;
    private TextView tv_title;
    private Button btn_done;

    Context mContext;

    private FrameLayout selected_photos_container_frame;
    private LinearLayout selected_photos_container;

    private TextView selected_photos_empty;
    private List<Uri> selectedUriList;
    private List<Uri> tempUriList;
    private Uri cameraImageUri;
    private RecyclerView rc_gallery;
    private PhotoEditor mPhotoEditor;
    private PhotoEditorView mPhotoEditorView;
    private PropertiesBSFragment mPropertiesBSFragment;
    private EmojiBSFragment mEmojiBSFragment;
    private StickerBSFragment mStickerBSFragment;
    private TextView mTxtCurrentTool;
    private static final String TAG = EditImageActivity.class.getSimpleName();
    ImageView imgUndo;
    ImageView imgRedo;
    ImageView imgCamera;
    ImageView imgGallery;
    ImageView imgSave;
    ImageView imgClose;

    private static final int CAMERA_REQUEST = 52;
    private static final int PICK_REQUEST = 53;
    private Typeface mWonderFont;
    private RecyclerView mRvTools, mRvFilters;
    private EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this);
    private FilterViewAdapter mFilterViewAdapter = new FilterViewAdapter(this);
    private ConstraintLayout mRootView;
    private ConstraintSet mConstraintSet = new ConstraintSet();
    private boolean mIsFilterVisible;


    SpannableStringBuilder ssb;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {


        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismissAllowingStateLoss();
            }


        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSavedInstanceState(savedInstanceState);

        pd = new SpotsDialog(getActivity(), R.style.Custom);


        dialog= new Dialog(getContext(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.activity_edit_image);
//        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        dialog.setCancelable(false);



        mPhotoEditorView = dialog.findViewById(R.id.photoEditorView);
        mTxtCurrentTool = dialog.findViewById(R.id.txtCurrentTool);
        mRvTools = dialog.findViewById(R.id.rvConstraintTools);
        mRvFilters = dialog.findViewById(R.id.rvFilterView);
        mRootView = dialog.findViewById(R.id.rootView);
        mRvFilters.setVisibility(View.GONE);

        imgUndo = dialog.findViewById(R.id.imgUndo);
        imgUndo.setOnClickListener(this);

        imgRedo = dialog.findViewById(R.id.imgRedo);
        imgRedo.setOnClickListener(this);

        imgCamera = dialog.findViewById(R.id.imgCamera);
        // imgCamera.setOnClickListener(this);
        imgCamera.setVisibility(View.GONE);

        imgGallery = dialog.findViewById(R.id.imgGallery);
        // imgGallery.setOnClickListener(this);
        imgGallery.setVisibility(View.GONE);
        imgSave = dialog.findViewById(R.id.imgSave);
        imgSave.setOnClickListener(this);

        imgClose = dialog.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(this);

        mWonderFont = Typeface.createFromAsset(getContext().getAssets(), "beyond_wonderland.ttf");

        /*img_bitmap=(ImageView) findViewById(R.id.img_bitmap);
        img_send=(ImageView) findViewById(R.id.img_send);*/

        mPropertiesBSFragment = new PropertiesBSFragment();
        mEmojiBSFragment = new EmojiBSFragment();
        mStickerBSFragment = new StickerBSFragment();
        mStickerBSFragment.setStickerListener(this);
        mEmojiBSFragment.setEmojiListener(this);
        mPropertiesBSFragment.setPropertiesChangeListener(this);

        LinearLayoutManager llmTools = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRvTools.setLayoutManager(llmTools);
        mRvTools.setAdapter(mEditingToolsAdapter);

        LinearLayoutManager llmFilters = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRvFilters.setLayoutManager(llmFilters);
        mRvFilters.setAdapter(mFilterViewAdapter);


        //Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);
        //Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");



        mPhotoEditor = new PhotoEditor.Builder(getContext(), mPhotoEditorView)
                .setPinchTextScalable(true) // set flag to make text scalable when pinch
                //.setDefaultTextTypeface(mTextRobotoTf)
                //.setDefaultEmojiTypeface(mEmojiTypeFace)
                .build(); // build photo editor sdk

        mPhotoEditor.setOnPhotoEditorListener(this);


    }

    private void setupSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            cameraImageUri = builder.selectedUri;
            tempUriList = builder.selectedUriList;
        } else {
            cameraImageUri = savedInstanceState.getParcelable(EXTRA_CAMERA_IMAGE_URI);
            tempUriList = savedInstanceState.getParcelableArrayList(EXTRA_CAMERA_SELECTED_IMAGE_URI);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(EXTRA_CAMERA_IMAGE_URI, cameraImageUri);
        outState.putParcelableArrayList(EXTRA_CAMERA_SELECTED_IMAGE_URI, new ArrayList<>(selectedUriList));
        super.onSaveInstanceState(outState);
    }

    public void show(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(this, getTag());
        ft.commitAllowingStateLoss();
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.tedbottompicker_content_view, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
            if (builder != null && builder.peekHeight > 0) {
                ((BottomSheetBehavior) behavior).setPeekHeight(builder.peekHeight);
            }

        }

        if (builder == null) {
            dismissAllowingStateLoss();
            return;
        }
        initView(contentView);

        setTitle();
        setRecyclerView();
        setSelectionView();

        selectedUriList = new ArrayList<>();


        if (builder.onImageSelectedListener != null && cameraImageUri != null) {
            addUri(cameraImageUri);
        } else if (builder.onMultiImageSelectedListener != null && tempUriList != null) {
            for (Uri uri : tempUriList) {
                addUri(uri);
            }
        }

        setDoneButton();
        checkMultiMode();
    }

    private void setSelectionView() {

        if (builder.emptySelectionText != null) {
            selected_photos_empty.setText(builder.emptySelectionText);
        }


    }

    private void setDoneButton() {

        if (builder.completeButtonText != null) {
            btn_done.setText(builder.completeButtonText);
        }

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onMultiSelectComplete();


            }
        });
    }

    private void onMultiSelectComplete() {

        if (selectedUriList.size() < builder.selectMinCount) {
            String message;
            if (builder.selectMinCountErrorText != null) {
                message = builder.selectMinCountErrorText;
            } else {
                message = String.format(getResources().getString(R.string.select_min_count), builder.selectMinCount);
            }

            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            return;
        }


        builder.onMultiImageSelectedListener.onImagesSelected(selectedUriList);
        dismissAllowingStateLoss();
    }

    private void checkMultiMode() {
        if (!isMultiSelect()) {
            btn_done.setVisibility(View.GONE);
            selected_photos_container_frame.setVisibility(View.GONE);
        }

    }

    private void initView(View contentView) {

        view_title_container = contentView.findViewById(R.id.view_title_container);
        rc_gallery = contentView.findViewById(R.id.rc_gallery);
        tv_title = contentView.findViewById(R.id.tv_title);
        btn_done = contentView.findViewById(R.id.btn_done);

        selected_photos_container_frame = contentView.findViewById(R.id.selected_photos_container_frame);
        selected_photos_container = contentView.findViewById(R.id.selected_photos_container);
        selected_photos_empty = contentView.findViewById(R.id.selected_photos_empty);
    }

    private void setRecyclerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rc_gallery.setLayoutManager(gridLayoutManager);
        rc_gallery.addItemDecoration(new GridSpacingItemDecoration(gridLayoutManager.getSpanCount(), builder.spacing, builder.includeEdgeSpacing));
        updateAdapter();
    }

    private void updateAdapter() {

        imageGalleryAdapter = new GalleryAdapter(
                getActivity()
                , builder);
        rc_gallery.setAdapter(imageGalleryAdapter);
        imageGalleryAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                GalleryAdapter.PickerTile pickerTile = imageGalleryAdapter.getItem(position);

                switch (pickerTile.getTileType()) {
                    case GalleryAdapter.PickerTile.CAMERA:
                        startCameraIntent();
                        break;
                    case GalleryAdapter.PickerTile.GALLERY:
                        startGalleryIntent();
                        break;
                    case GalleryAdapter.PickerTile.IMAGE:
                        if (pickerTile.getImageUri() != null) {
                            complete(pickerTile.getImageUri());
                        }

                        break;

                    default:
                        errorMessage();
                }

            }
        });
    }

    private void complete(final Uri uri) {
        if (isMultiSelect()) {
            if (selectedUriList.contains(uri)) {
                removeImage(uri);
            } else {
                addUri(uri);
            }

        } else {
            builder.onImageSelectedListener.onImageSelected(uri);
            dismissAllowingStateLoss();
        }

    }

    private void addUri(final Uri uri) {
        if (selectedUriList.size() == builder.selectMaxCount) {
            String message;
            if (builder.selectMaxCountErrorText != null) {
                message = builder.selectMaxCountErrorText;
            } else {
                message = String.format(getResources().getString(R.string.select_max_count), builder.selectMaxCount);
            }

            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            return;
        }


        selectedUriList.add(uri);

        ImageView thumbnail = null;
        ImageView iv_close = null;
        try {
            final View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.tedbottompicker_selected_item, null);
            thumbnail = rootView.findViewById(R.id.selected_photo);
            iv_close = rootView.findViewById(R.id.iv_close);
            rootView.setTag(uri);

            selected_photos_container.addView(rootView, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        int px = (int) getResources().getDimension(R.dimen.tedbottompicker_selected_image_height);
        thumbnail.setLayoutParams(new FrameLayout.LayoutParams(px, px));

        if (builder.imageProvider == null) {
            Glide.with(getActivity())
                    .load(uri)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.ic_gallery)
                            .error(R.drawable.img_error))
                    .into(thumbnail);
        } else {
            builder.imageProvider.onProvideImage(thumbnail, uri);
        }


        if (builder.deSelectIconDrawable != null) {
            iv_close.setImageDrawable(builder.deSelectIconDrawable);
        }

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeImage(uri);

            }
        });
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MLMLMLM",""+uri);
               /* String stringUri;
                stringUri = uri.toString();
                Log.e("MLMLMLM",""+uri);*/
                pd.show();
                uri_selected=uri;
                alert_image_picker(uri);
                /*removeImage(uri);*/

            }
        });



        updateSelectedView();
        imageGalleryAdapter.setSelectedUriList(selectedUriList, uri);

    }

    private void removeImage(Uri uri) {

        selectedUriList.remove(uri);


        for (int i = 0; i < selected_photos_container.getChildCount(); i++) {
            View childView = selected_photos_container.getChildAt(i);


            if (childView.getTag().equals(uri)) {
                selected_photos_container.removeViewAt(i);
                break;
            }
        }

        updateSelectedView();
        imageGalleryAdapter.setSelectedUriList(selectedUriList, uri);
    }

    private void updateSelectedView() {

        if (selectedUriList == null || selectedUriList.size() == 0) {
            selected_photos_empty.setVisibility(View.VISIBLE);
            selected_photos_container.setVisibility(View.GONE);
        } else {
            selected_photos_empty.setVisibility(View.GONE);
            selected_photos_container.setVisibility(View.VISIBLE);
        }

    }

    private void startCameraIntent() {
        Intent cameraInent;
        File mediaFile;

        if (builder.mediaType == BaseBuilder.MediaType.IMAGE) {
//            cameraInent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            mediaFile = getImageFile();
//            cameraInent.putExtra(MediaStore.EXTRA_OUTPUT, mediaFile);

            Log.e("GGHHTTESSS","working");
            cameraInent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mediaFile = getImageFile();
            cameraInent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mediaFile));


        } else {
            cameraInent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            mediaFile = getVideoFile();
        }

        if (cameraInent.resolveActivity(getActivity().getPackageManager()) == null) {
            errorMessage("This Application do not have Camera Application");
            return;
        }


        Uri photoURI = FileProvider.getUriForFile(getContext(), "com.rentokil.pci.rauditor_vn.fileprovider", mediaFile);

        List<ResolveInfo> resolvedIntentActivities = getContext().getPackageManager().queryIntentActivities(cameraInent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
            String packageName = resolvedIntentInfo.activityInfo.packageName;
            getContext().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        cameraInent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        TedOnActivityResult.with(getActivity())
                .setIntent(cameraInent)
                .setListener(new OnActivityResultListener() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            onActivityResultCamera(cameraImageUri);
                        }
                    }
                })
                .startActivityForResult();
    }

    private File getImageFile() {
        // Create an image file name
        File imageFile = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                /*storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);*/
                storageDir = new File(Environment.getExternalStorageDirectory(), "rAuditor_IN");

            }
            if (!storageDir.exists())
                storageDir.mkdirs();
            imageFile = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            cameraImageUri = Uri.fromFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage("Could not create imageFile for camera");
        }


        return imageFile;
    }

    private File getVideoFile() {
        // Create an image file name
        File videoFile = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "VIDEO_" + timeStamp + "_";
            File storageDir = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
            }

            if (!storageDir.exists())
                storageDir.mkdirs();

            videoFile = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".mp4",         /* suffix */
                    storageDir      /* directory */
            );


            // Save a file: path for use with ACTION_VIEW intents
            cameraImageUri = Uri.fromFile(videoFile);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage("Could not create imageFile for camera");
        }


        return videoFile;
    }

    private void errorMessage(String message) {
        String errorMessage = message == null ? "Something wrong." : message;

        if (builder.onErrorListener == null) {
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
        } else {
            builder.onErrorListener.onError(errorMessage);
        }
    }

    private void startGalleryIntent() {
        Intent galleryIntent;
        Uri uri;
        if (builder.mediaType == BaseBuilder.MediaType.IMAGE) {
            galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryIntent.setType("image/*");
        } else {
            galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            galleryIntent.setType("video/*");

        }

        if (galleryIntent.resolveActivity(getActivity().getPackageManager()) == null) {
            errorMessage("This Application do not have Gallery Application");
            return;
        }

        TedOnActivityResult.with(getActivity())
                .setIntent(galleryIntent)
                .setListener(new OnActivityResultListener() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            onActivityResultGallery(data);
                        }
                    }
                })
                .startActivityForResult();
    }

    private void errorMessage() {
        errorMessage(null);
    }

    private void setTitle() {

        if (!builder.showTitle) {
            tv_title.setVisibility(View.GONE);

            if (!isMultiSelect()) {
                view_title_container.setVisibility(View.GONE);
            }

            return;
        }

        if (!TextUtils.isEmpty(builder.title)) {
            tv_title.setText(builder.title);
        }

        if (builder.titleBackgroundResId > 0) {
            tv_title.setBackgroundResource(builder.titleBackgroundResId);
        }

    }

    private boolean isMultiSelect() {
        return builder.onMultiImageSelectedListener != null;
    }

    private void onActivityResultCamera(final Uri cameraImageUri) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            MediaScannerConnection.scanFile(getContext(), new String[]{cameraImageUri.getPath()}, new String[]{"image/jpeg"}, new MediaScannerConnection.MediaScannerConnectionClient() {
                @Override
                public void onMediaScannerConnected() {

                }

                @Override
                public void onScanCompleted(String s, Uri uri) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateAdapter();
                            complete(cameraImageUri);
                        }
                    });

                }
            });
        }
    }


    private void onActivityResultGallery(Intent data) {
        Uri temp = data.getData();

        if (temp == null) {
            errorMessage();
        }

        String realPath = RealPathUtil.getRealPath(getActivity(), temp);

        Uri selectedImageUri;
        try {
            selectedImageUri = Uri.fromFile(new File(realPath));
        } catch (Exception ex) {
            selectedImageUri = Uri.parse(realPath);
        }

        complete(selectedImageUri);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.imgUndo) {
            mPhotoEditor.undo();
        } else if (id == R.id.imgRedo) {
            mPhotoEditor.redo();
        } else if (id == R.id.imgSave) {
            saveImage(uri_selected);
        } else if (id == R.id.imgClose) {
            dialog.dismiss();
//
        } else if (id == R.id.imgCamera) {/*EnableRuntimePermission();*/
           /* Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
        } else if (id == R.id.imgGallery) {
           /* Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_REQUEST);*/
        }
    }



    @Override
    public void onEmojiClick(String emojiUnicode) {
        mPhotoEditor.addEmoji(emojiUnicode);
        mTxtCurrentTool.setText(R.string.label_emoji);

    }

    @Override
    public void onColorChanged(int colorCode) {
        mPhotoEditor.setBrushColor(colorCode);
        mTxtCurrentTool.setText(R.string.label_brush);
    }

    @Override
    public void onOpacityChanged(int opacity) {
        mPhotoEditor.setOpacity(opacity);
        mTxtCurrentTool.setText(R.string.label_brush);
    }

    @Override
    public void onBrushSizeChanged(int brushSize) {
        mPhotoEditor.setBrushSize(brushSize);
        mTxtCurrentTool.setText(R.string.label_brush);
    }
    @Override
    public void onStickerClick(Bitmap bitmap) {
        mPhotoEditor.addImage(bitmap);
        mTxtCurrentTool.setText(R.string.label_sticker);
    }

    @Override
    public void onFilterSelected(PhotoFilter photoFilter) {
        mPhotoEditor.setFilterEffect(photoFilter);
    }

    @Override
    public void onToolSelected(ToolType toolType) {
        switch (toolType) {
            case BRUSH:
                mPhotoEditor.setBrushDrawingMode(true);
                mTxtCurrentTool.setText(R.string.label_brush);
                mPropertiesBSFragment.show(getFragmentManager(), mPropertiesBSFragment.getTag());
                break;
            case TEXT:
         /*TextEditorDialogFragment textEditorDialogFragment = TextEditorDialogFragment.show(getContext(),"");
                textEditorDialogFragment.setOnTextEditorListener(new TextEditorDialogFragment.TextEditor() {
                    @Override
                    public void onDone(String inputText, int colorCode) {
                        final TextStyleBuilder styleBuilder = new TextStyleBuilder();
                        styleBuilder.withTextColor(colorCode);

                        mPhotoEditor.addText(inputText, styleBuilder);
                        mTxtCurrentTool.setText(R.string.label_text);
                    }
                });*/
                break;
            case ERASER:
                mPhotoEditor.brushEraser();
                mTxtCurrentTool.setText(R.string.label_eraser);
                break;
            case FILTER:
                mTxtCurrentTool.setText(R.string.label_filter);
                showFilter(true);
                break;
            case EMOJI:
              //  mEmojiBSFragment.show(getSupportFragmentManager(), mEmojiBSFragment.getTag());
                mEmojiBSFragment.show(getFragmentManager(), mEmojiBSFragment.getTag());
                break;
            case STICKER:
                mStickerBSFragment.show(getFragmentManager(), mStickerBSFragment.getTag());
                break;
        }
    }

    @Override
    public void onEditTextChangeListener(final View rootView, String text, int colorCode) {
       /* TextEditorDialogFragment textEditorDialogFragment =
                TextEditorDialogFragment.show(TedBottomSheetDialogFragment.this.getContext().getApplicationContext(), text, colorCode);
        textEditorDialogFragment.setOnTextEditorListener(new TextEditorDialogFragment.TextEditor() {
            @Override
            public void onDone(String inputText, int colorCode) {
                final TextStyleBuilder styleBuilder = new TextStyleBuilder();
                styleBuilder.withTextColor(colorCode);

                mPhotoEditor.editText(rootView, inputText, styleBuilder);
                mTxtCurrentTool.setText(R.string.label_text);
            }
        });*/
    }

    @Override
    public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {
        Log.d(TAG, "onAddViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + numberOfAddedViews + "]");
    }

    @Override
    public void onRemoveViewListener(ViewType viewType, int numberOfAddedViews) {
        Log.d(TAG, "onRemoveViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + numberOfAddedViews + "]");
    }



    @Override
    public void onStartViewChangeListener(ViewType viewType) {
        Log.d(TAG, "onStartViewChangeListener() called with: viewType = [" + viewType + "]");
    }

    @Override
    public void onStopViewChangeListener(ViewType viewType) {
        Log.d(TAG, "onStopViewChangeListener() called with: viewType = [" + viewType + "]");
    }


    public interface OnMultiImageSelectedListener {
        void onImagesSelected(List<Uri> uriList);
    }

    public interface OnImageSelectedListener {
        void onImageSelected(Uri uri);
    }

    public interface OnErrorListener {
        void onError(String message);
    }

    public interface ImageProvider {
        void onProvideImage(ImageView imageView, Uri imageUri);
    }

    public abstract static class BaseBuilder<T extends BaseBuilder> {

        public int previewMaxCount = 25;
        public Drawable cameraTileDrawable;
        public Drawable galleryTileDrawable;
        public Drawable selectedForegroundDrawable;
        public ImageProvider imageProvider;
        public boolean showCamera = true;
        public boolean showGallery = true;
        public int cameraTileBackgroundResId = R.color.tedbottompicker_camera;
        public int galleryTileBackgroundResId = R.color.tedbottompicker_gallery;
        @MediaType
        public int mediaType = MediaType.IMAGE;
        protected FragmentActivity fragmentActivity;
        OnImageSelectedListener onImageSelectedListener;
        OnMultiImageSelectedListener onMultiImageSelectedListener;
        OnErrorListener onErrorListener;
        private String title;
        private boolean showTitle = true;
        private List<Uri> selectedUriList;
        private Uri selectedUri;
        private Drawable deSelectIconDrawable;
        private int spacing = 1;
        private boolean includeEdgeSpacing = false;
        private int peekHeight = -1;
        private int titleBackgroundResId;
        private int selectMaxCount = Integer.MAX_VALUE;
        private int selectMinCount = 0;
        private String completeButtonText;
        private String emptySelectionText;
        private String selectMaxCountErrorText;
        private String selectMinCountErrorText;

        public BaseBuilder(@NonNull FragmentActivity fragmentActivity) {

            this.fragmentActivity = fragmentActivity;

            setCameraTile(R.drawable.ic_camera);
            setGalleryTile(R.drawable.ic_gallery);
            setSpacingResId(R.dimen.tedbottompicker_grid_layout_margin);
        }

        public T setCameraTile(@DrawableRes int cameraTileResId) {
            setCameraTile(ContextCompat.getDrawable(fragmentActivity, cameraTileResId));
            return (T) this;
        }

        public BaseBuilder<T> setGalleryTile(@DrawableRes int galleryTileResId) {
            setGalleryTile(ContextCompat.getDrawable(fragmentActivity, galleryTileResId));
            return this;
        }

        public T setSpacingResId(@DimenRes int dimenResId) {
            this.spacing = fragmentActivity.getResources().getDimensionPixelSize(dimenResId);
            return (T) this;
        }

        public T setCameraTile(Drawable cameraTileDrawable) {
            this.cameraTileDrawable = cameraTileDrawable;
            return (T) this;
        }

        public T setGalleryTile(Drawable galleryTileDrawable) {
            this.galleryTileDrawable = galleryTileDrawable;
            return (T) this;
        }

        public T setDeSelectIcon(@DrawableRes int deSelectIconResId) {
            setDeSelectIcon(ContextCompat.getDrawable(fragmentActivity, deSelectIconResId));
            return (T) this;
        }

        public T setDeSelectIcon(Drawable deSelectIconDrawable) {
            this.deSelectIconDrawable = deSelectIconDrawable;
            return (T) this;
        }

        public T setSelectedForeground(@DrawableRes int selectedForegroundResId) {
            setSelectedForeground(ContextCompat.getDrawable(fragmentActivity, selectedForegroundResId));
            return (T) this;
        }

        public T setSelectedForeground(Drawable selectedForegroundDrawable) {
            this.selectedForegroundDrawable = selectedForegroundDrawable;
            return (T) this;
        }

        public T setPreviewMaxCount(int previewMaxCount) {
            this.previewMaxCount = previewMaxCount;
            return (T) this;
        }

        public T setSelectMaxCount(int selectMaxCount) {
            this.selectMaxCount = selectMaxCount;
            return (T) this;
        }

        public T setSelectMinCount(int selectMinCount) {
            this.selectMinCount = selectMinCount;
            return (T) this;
        }

        public T setOnImageSelectedListener(OnImageSelectedListener onImageSelectedListener) {
            this.onImageSelectedListener = onImageSelectedListener;
            return (T) this;
        }

        public T setOnMultiImageSelectedListener(OnMultiImageSelectedListener onMultiImageSelectedListener) {
            this.onMultiImageSelectedListener = onMultiImageSelectedListener;
            return (T) this;
        }

        public T setOnErrorListener(OnErrorListener onErrorListener) {
            this.onErrorListener = onErrorListener;
            return (T) this;
        }

        public T showCameraTile(boolean showCamera) {
            this.showCamera = showCamera;
            return (T) this;
        }

        public T showGalleryTile(boolean showGallery) {
            this.showGallery = showGallery;
            return (T) this;
        }

        public T setSpacing(int spacing) {
            this.spacing = spacing;
            return (T) this;
        }

        public T setIncludeEdgeSpacing(boolean includeEdgeSpacing) {
            this.includeEdgeSpacing = includeEdgeSpacing;
            return (T) this;
        }

        public T setPeekHeight(int peekHeight) {
            this.peekHeight = peekHeight;
            return (T) this;
        }

        public T setPeekHeightResId(@DimenRes int dimenResId) {
            this.peekHeight = fragmentActivity.getResources().getDimensionPixelSize(dimenResId);
            return (T) this;
        }

        public T setCameraTileBackgroundResId(@ColorRes int colorResId) {
            this.cameraTileBackgroundResId = colorResId;
            return (T) this;
        }

        public T setGalleryTileBackgroundResId(@ColorRes int colorResId) {
            this.galleryTileBackgroundResId = colorResId;
            return (T) this;
        }

        public T setTitle(String title) {
            this.title = title;
            return (T) this;
        }

        public T setTitle(@StringRes int stringResId) {
            this.title = fragmentActivity.getResources().getString(stringResId);
            return (T) this;
        }

        public T showTitle(boolean showTitle) {
            this.showTitle = showTitle;
            return (T) this;
        }

        public T setCompleteButtonText(String completeButtonText) {
            this.completeButtonText = completeButtonText;
            return (T) this;
        }

        public T setCompleteButtonText(@StringRes int completeButtonResId) {
            this.completeButtonText = fragmentActivity.getResources().getString(completeButtonResId);
            return (T) this;
        }

        public T setEmptySelectionText(String emptySelectionText) {
            this.emptySelectionText = emptySelectionText;
            return (T) this;
        }

        public T setEmptySelectionText(@StringRes int emptySelectionResId) {
            this.emptySelectionText = fragmentActivity.getResources().getString(emptySelectionResId);
            return (T) this;
        }

        public T setSelectMaxCountErrorText(String selectMaxCountErrorText) {
            this.selectMaxCountErrorText = selectMaxCountErrorText;
            return (T) this;
        }

        public T setSelectMaxCountErrorText(@StringRes int selectMaxCountErrorResId) {
            this.selectMaxCountErrorText = fragmentActivity.getResources().getString(selectMaxCountErrorResId);
            return (T) this;
        }

        public T setSelectMinCountErrorText(String selectMinCountErrorText) {
            this.selectMinCountErrorText = selectMinCountErrorText;
            return (T) this;
        }

        public T setSelectMinCountErrorText(@StringRes int selectMinCountErrorResId) {
            this.selectMinCountErrorText = fragmentActivity.getResources().getString(selectMinCountErrorResId);
            return (T) this;
        }

        public T setTitleBackgroundResId(@ColorRes int colorResId) {
            this.titleBackgroundResId = colorResId;
            return (T) this;
        }

        public T setImageProvider(ImageProvider imageProvider) {
            this.imageProvider = imageProvider;
            return (T) this;
        }

        public T setSelectedUriList(List<Uri> selectedUriList) {
            this.selectedUriList = selectedUriList;
            return (T) this;
        }

        public T setSelectedUri(Uri selectedUri) {
            this.selectedUri = selectedUri;
            return (T) this;
        }

        public T showVideoMedia() {
            this.mediaType = MediaType.VIDEO;
            return (T) this;
        }

        public TedBottomSheetDialogFragment create() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                    && ContextCompat.checkSelfPermission(fragmentActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                throw new RuntimeException("Missing required WRITE_EXTERNAL_STORAGE permission. Did you remember to request it first?");
            }

            if (onImageSelectedListener == null && onMultiImageSelectedListener == null) {
                throw new RuntimeException("You have to use setOnImageSelectedListener() or setOnMultiImageSelectedListener() for receive selected Uri");
            }

            TedBottomSheetDialogFragment customBottomSheetDialogFragment = new TedBottomSheetDialogFragment();
            customBottomSheetDialogFragment.builder = (T) this;
            return customBottomSheetDialogFragment;
        }

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({MediaType.IMAGE, MediaType.VIDEO})
        public @interface MediaType {
            int IMAGE = 1;
            int VIDEO = 2;
        }


    }


    private void alert_image_picker(Uri uri){



        Log.e("AAXXXXXVDSS","uri 1= "+uri);
    //    Bitmap bmImg2 = BitmapFactory.decodeFile(uri.);
        try {
            Log.e("MLMLMLM\tin",""+uri.getPath());


            int orientation = -1;

            ExifInterface exif = new ExifInterface(uri.getPath());

            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                 ExifInterface.ORIENTATION_NORMAL);

            Log.e("HHGGGDAAXX","exif = "+exifOrientation);

            if(exifOrientation==6){



                Log.e("HHGGGDAAXX","enter 6 ");
                Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri );

                mPhotoEditorView.getSource().setImageBitmap(rotateBitmaps(bitmap_x,90));

                pd.dismiss();
            }

            else{
                Log.e("HHGGGDAAXX","enter all ");
                Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri );

                mPhotoEditorView.getSource().setImageBitmap(bitmap_x);

                pd.dismiss();
            }



//            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
//                    ExifInterface.ORIENTATION_NORMAL);
//
//
//
//
//
//            if(exifOrientation==1){
//
//
//            }
//            else{
//
//                Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri );
//
//                Log.e("AAXXSSWDDDCX","rotate = "+rotateImage(bitmap_x,90));
//                mPhotoEditorView.getSource().setImageBitmap(bitmap_x);
//            }



        } catch (IOException e) {
            Log.e("MLMLMLM\terr",""+e.getMessage());
            pd.dismiss();
            e.printStackTrace();
        }

/*
        Log.e("MLMLMLM\t",""+imgFile);
        Log.e("MLMLMLM\t",""+imgFile.exists());*/
        /*mPhotoEditorView.getSource().setImageBitmap(bmImg2);*/
        dialog.show();

    }

    public Bitmap rotateBitmaps(Bitmap original, float degrees) {
        Matrix matrix = new Matrix();
        matrix.preRotate(degrees);
        Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true);
        original.recycle();
        return rotatedBitmap;
    }

    public static Bitmap rotateImage(Bitmap src, float degree)
    {
        // create new matrix
        Matrix matrix = new Matrix();
        // setup rotation degree
        matrix.postRotate(degree);
        Bitmap bmp = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        return bmp;
    }



    void showFilter(boolean isVisible) {
        mIsFilterVisible = isVisible;
        mConstraintSet.clone(mRootView);

        if (isVisible) {
            mConstraintSet.clear(mRvFilters.getId(), ConstraintSet.START);
            mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.START,
                    ConstraintSet.PARENT_ID, ConstraintSet.START);
            mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.END,
                    ConstraintSet.PARENT_ID, ConstraintSet.END);
        } else {
            mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.START,
                    ConstraintSet.PARENT_ID, ConstraintSet.END);
            mConstraintSet.clear(mRvFilters.getId(), ConstraintSet.END);
        }

        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(350);
        changeBounds.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        TransitionManager.beginDelayedTransition(mRootView, changeBounds);

        mConstraintSet.applyTo(mRootView);
    }


    private void saveImage(Uri uri) {


        File myDirectory = new File(Environment.getExternalStorageDirectory(), "rAuditor_SG");

        if(!myDirectory.exists()) {
            myDirectory.mkdirs();
        }

        File file = new File(myDirectory.getPath()
                + File.separator + ""
                + System.currentTimeMillis() + ".png");

        Log.e("JJMMGGGVVV","file create = "+myDirectory.exists());
        Str_IMAGE_1_URL = file.getAbsolutePath();

        dialog.dismiss();
        try {
            file.createNewFile();



            SaveSettings saveSettings = new SaveSettings.Builder()
                    .setClearViewsEnabled(true)
                    .setTransparencyEnabled(true)
                    .build();



            mPhotoEditor.saveAsFile(file.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
                @Override
                public void onSuccess(@NonNull String imagePath) {

                    mPhotoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));
                    Bitmap bmImg1 = BitmapFactory.decodeFile(Str_IMAGE_1_URL);
                   /* Image_layout_Q1.setImageBitmap(bmImg1);
                    Image_layout_Q1.setVisibility(View.VISIBLE);
                    Q1_Imageview.setVisibility(View.GONE);*/
                    dialog.dismiss();


                    if (uri_selected!=null) {
                        removeImage(uri_selected);
                        addUri(Uri.fromFile(new File(imagePath)));
                    }


                }

                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e("AHHASGGSGSXX11","error= "+exception.getMessage());
                    dialog.dismiss();
                }
            });
        } catch (IOException e) {

            Log.e("AHHASGGSGSXX","error= "+e.getMessage());
            dialog.dismiss();
            e.printStackTrace();

        }

    }

}
