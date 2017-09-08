package application;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class SiegeClass extends Application {
	final int WIDTH = 700;
	final int HEIGHT = 400;
	int ClientWidth = WIDTH;
	int ClientHeight = HEIGHT;

	int mouse_x;
	int mouse_y;

	int illust_width = 150;
	int illust_height = 100;
	double []illust_x = {
			WIDTH/2 - illust_height,
			WIDTH/2 - illust_height + 200,
			WIDTH/2 - illust_height,
			WIDTH/2 - illust_height - 200,
			WIDTH/2 - illust_height + 200,
			WIDTH/2 - illust_height,
			WIDTH/2 - illust_height - 200,
	};
	double []illust_y = {
			250,
			50,
			50,
			50,
			150,
			150,
			150,
	};

//	ArrayList<Line> line = new ArrayList<Line>();
	final Canvas canvas = new Canvas(WIDTH,HEIGHT);
//	streamTest stream = new streamTest();
	Group root = new Group();
//	AnchorPane center = new AnchorPane();

//    Label label = new Label("Click anywhere.");
	@Override
	public void start(Stage stage) throws Exception {

//		addMouseListener(this);
//	    Button      btn     = new Button( "ColorType1");
//	    btn.setMaxSize(100, 20);
//	    root.getChildren().add( btn );
//		root.getChildren().add(label);
        Scene   scene   = new Scene(root);

		// 選択枠
		Image select_img = new Image(getClass().getResourceAsStream("select.png"));
		ImageView select = new ImageView(); // 初めは消しておく
		select.setFitHeight(110);
		select.setFitWidth(160);
		select.relocate( 225 -2.5 , 160 -2.5);
		root.getChildren().add(select);


		// 背景
//	    Image img = new Image( Paths.get("etc/back.png").toUri().toString());
//	    Background bg = new Background(new BackgroundImage(img, null, null, null, null));
//	    center.setBackground(bg);

        /* アクションイベント、キーイベントの使い方を確認 */
        // ボタンに押下処理を追加する
//        EventHandler<KeyEvent>      btnActionFilter = ( event ) -> {
//        	System.out.println( "button push!" ); event.consume();
//    	};
//        btn.addEventHandler( KeyEvent.ANY , btnActionFilter );

        // シーンにマウスクリック処理用のイベント・ハンドラを設定
        EventHandler<MouseEvent>   sceneHandler     = ( event ) -> {
        	// ボタンのクリックによる動作
        	for(int bt = 0;bt < 7;bt++){
        		if(
    				(int)event.getSceneX() < illust_x[bt] + 150 &&
					(int)event.getSceneX() > illust_x[bt]  &&
					(int)event.getSceneY() < illust_y[bt] + 100 &&
					(int)event.getSceneY() > illust_y[bt]
				){
        			// ボタンが押されたときの処理
        			select.setImage(select_img);
					select.relocate( illust_x[bt] -5, illust_y[bt] -5);

					String server_name = null;
					switch(bt){
					case 0:
						server_name = "default";
						break;
					case 1:
						server_name = "eus";
						break;
					case 2:
						server_name = "seas";
						break;
					case 3:
						server_name = "wus";
						break;
					case 4:
						server_name = "neu";
						break;
					case 5:
						server_name = "eau";
						break;
					case 6:
						server_name = "weu";
						break;
					}
					WriteFunc(bt,server_name);
        		}
        	}
    	};
        scene.addEventHandler( MouseEvent.MOUSE_PRESSED , sceneHandler );

        // シーンにマウス動作(移動)時のイベントを追加する
        EventHandler<MouseEvent>      sceneMouseFilter  = ( event ) -> {
        };
        scene.addEventFilter( MouseEvent.MOUSE_MOVED , sceneMouseFilter );

		stage.setTitle("SiegeSeverSelect");
		stage.setWidth(WIDTH);
		stage.setHeight(HEIGHT);
		stage.initStyle(StageStyle.UTILITY); // クローズボックスのみの構成
		stage.centerOnScreen(); // 画面中央に配置
		stage.setResizable(false); //画面サイズ変更不可能

		canvas.setOnKeyPressed(event -> onKeyPressed(event));
		canvas.setFocusTraversable(true);

		root.getChildren().add(canvas);

		stage.setScene(scene);
		stage.show();

		ClientHeight = (int) stage.getScene().getHeight();
		ClientWidth = (int) stage.getScene().getWidth();

		Timer timer = new Timer();
		class GameTask extends TimerTask {
			@Override
			public void run(){
			}
		}

		GameTask task = new GameTask();
		stage.setOnCloseRequest(event -> {
			if(task != null)task.cancel();
			if(timer != null)timer.cancel();
		});

		timer.schedule(task, 1000, 100);
		Toolkit.getDefaultToolkit().beep();

		Init();
	}
	void onKeyPressed(KeyEvent event) {
		if(event.getCode()==KeyCode.ESCAPE ){
		}
	}

	public void Init(){
		/*
		URL url[] = {
				this.getClass().getResource("src/etc/1.png"),
				this.getClass().getResource("src/etc/2.png"),
				this.getClass().getResource("src/etc/3.png"),
				this.getClass().getResource("src/etc/4.png"),
				this.getClass().getResource("src/etc/5.png"),
				this.getClass().getResource("src/etc/6.png"),
				this.getClass().getResource("src/etc/7.png"),
		};
		*/
		Image []illust_tmp = {
				new Image(getClass().getResourceAsStream("1.png")),
				new Image(getClass().getResourceAsStream("2.png")),
				new Image(getClass().getResourceAsStream("3.png")),
				new Image(getClass().getResourceAsStream("4.png")),
				new Image(getClass().getResourceAsStream("5.png")),
				new Image(getClass().getResourceAsStream("6.png")),
				new Image(getClass().getResourceAsStream("7.png")),
/*
				new Image(Paths.get("etc/1.png").toUri().toString()),
				new Image(Paths.get("etc/2.png").toUri().toString()),
				new Image(Paths.get("etc/3.png").toUri().toString()),
				new Image(Paths.get("src/etc/4.png").toUri().toString()),
				new Image(Paths.get("src/etc/5.png").toUri().toString()),
				new Image(Paths.get("src/etc/6.png").toUri().toString()),
				new Image(Paths.get("src/etc/7.png").toUri().toString()),
*/
		};
		for(int i = 0; i < 7 ; i++){
			ImageView illust_img = new ImageView(illust_tmp[i]);
			illust_img.setFitHeight(illust_height);
			illust_img.setFitWidth(illust_width);
			illust_img.relocate(illust_x[i],illust_y[i]);
			root.getChildren().add(illust_img);
		}
	}
	public void WriteFunc(int select_bt,String _server){ // 選択されたボタンのサーバー名を引数に持つ
		 // Fileクラスをインスタンス化
        File file = new File("../GameSettings.ini" );

        // ファイルが存在するかどうかを判定
        if ( !file.exists() ) {
            // ファイルが存在しない場合は処理終了
            System.out.println( "ファイルが存在しない" );
            return;
        }

        // 指定されたパスがファイルかどうかを判定
        if ( !file.isFile() ) {
            // ディレクトリを指定した場合は処理終了
            System.out.println( "ファイル以外を指定" );
            return;
        }

        // ファイルが読み込み可能かどうかを判定
        if ( !file.canRead() ) {
            // ファイルが読み込み不可の場合は処理終了
            System.out.println("ファイルが読み込み不可");
            return;
        }

        // ファイルが書き込み可能かどうかを判定
        if ( !file.canWrite() ) {
            // ファイルが読み込み不可の場合は処理終了
            System.out.println("ファイルが読み込み不可");
            return;
        }

//        int gyo = Integer.parseInt( para1 );

        String readText = fileRead( file,_server);

//        String replaceText = readText.replaceAll(para2, para3);

        fileWrite( file, readText );
	}

    private static String fileRead(File _file,String _server) {

        StringBuffer fileRead = new StringBuffer("");

        try {

            // FileReaderクラスをインスタンス化
            FileReader fr = new FileReader( _file );

            // BufferedReaderクラスをインスタンス化
            BufferedReader br = new BufferedReader( fr );

            String str = null;
            String template = "DataCenterHint=";
            boolean go_change = false;
            while ( ( str = br.readLine() ) != null ) {
            	switch(str){
            		case "DataCenterHint=default":
            		case "DataCenterHint=eus":
            		case "DataCenterHint=cus":
            		case "DataCenterHint=scus":
            		case "DataCenterHint=wus":
            		case "DataCenterHint=sbr":
            		case "DataCenterHint=neu":
            		case "DataCenterHint=weu":
            		case "DataCenterHint=eas":
            		case "DataCenterHint=seas":
            		case "DataCenterHint=eau":
            		case "DataCenterHint=wja":
            			go_change = true;
            	}
            	if(go_change == false){
                    fileRead.append(str + "\r\n");
                } else {
                    System.out.println( str+"：を削除" );
                    System.out.println( _server+"：を追加" );
                    fileRead.append(template + _server + "\r\n");
                }
            	go_change = false;
            }

            // ファイルを閉じる
            br.close();

        } catch ( FileNotFoundException ex ) {
             System.out.println( ex );
        } catch ( IOException ex ) {
             System.out.println( ex );
        }
        return fileRead.toString();
    }

    private static void fileWrite(File _file, String _text){

        try {

            // FileWriterクラスをインスタンス化
            FileWriter filewriter = new FileWriter( _file );

            filewriter.write( _text );

            // ファイルを閉じる
            filewriter.close();

        } catch ( IOException ex ) {
             System.out.println( ex );
        }
    }
	public static void main(String[] args) {
	  	launch(args);
	}

}
