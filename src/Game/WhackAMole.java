package Game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.UUID;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;


/**
 *
 * @author sopian
 */
public class WhackAMole extends JFrame {
    private JPanel panel;    
    private JLabel[] holes = new JLabel[16];
    private int[] board = new int[16];

    private int score = 0;
    private int timeLeft = 10;
    private int highscore = 0;

    private JLabel lblScore;
    private JLabel lblTimeLeft;
    private JLabel lblHighscore;
    private JLabel leaderboard;
    private JButton btnStart;
    private JTextField nametext;
    private Timer timer;
    
    ArrayList<String> listBoard = new ArrayList<String>();
    ArrayList<String> listName = new ArrayList<String>();
    
    private Image screenImage;
    private Graphics screenGraphic;						
    private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

    //Tombol	
    //Tombol Exit
    private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/exitButtonEntered.png"));
    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("/images/exitButtonBasic.png"));
    private JButton exitButton = new JButton(exitButtonBasicImage); //basic default button is exitButtonBasicImage

    //Tombol Start
    private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/startButtonEntered.png"));
    private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("/images/startButtonBasic.png"));
    private JButton startButton = new JButton(startButtonBasicImage);

    //Tombol Quit
    private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/quitButtonEntered.png"));
    private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("/images/quitButtonBasic.png"));
    private JButton quitButton = new JButton(quitButtonBasicImage);

    //Tombol Easy
    private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/easyButtonEntered.png"));
    private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("/images/easyButtonBasic.png"));
    private JButton easyButton = new JButton(easyButtonBasicImage);
    
    //Tombol Normal
    private ImageIcon normalButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/normalButtonEntered.png"));
    private ImageIcon normalButtonBasicImage = new ImageIcon(Main.class.getResource("/images/normalButtonBasic.png"));
    private JButton normalButton = new JButton(normalButtonBasicImage);

    //Hard Button 
    private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/hardButtonEntered.png"));
    private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("/images/hardButtonBasic.png"));
    private JButton hardButton = new JButton(hardButtonBasicImage);

    //Tombol Back
    private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/backButtonEntered.png"));
    private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("/images/backButtonBasic.png"));
    private JButton backButton = new JButton(backButtonBasicImage);

    //panel content
    private JPanel content = new JPanel();    
    //
    private JPanel contentList = new JPanel();

    //Agar dapat dipindahkan layarnya
    private int mouseX, mouseY;

    //Layar
    private boolean isMainScreen = false; //initially not on main screen therefore false

    //Kelas Track
    //Membuat arraylist yang dapat tetap menjalankan track sesuai dengan judul dan track yang ditentukan
    ArrayList<Track> trackList = new ArrayList<Track>();

    //Title
    JLabel lblTitle = new JLabel("Whack A Mole");
    
    //List nama
    JList leada;
    //List score
    JList leadb;

    private Image startImage;
    private Image selectedImage;
    private Music selectedMusic;
    private boolean isVisible;
    private Music introMusic = new Music("introMusic.mp3", true);
    private int nowSelected = 0; //index 0 (first track)

    public WhackAMole() {
        setUndecorated(true); // Menu Bar tidak ditampilkan saat pertama kali dieksekusi
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false); // User tidak dapat mengatur ulang ukuran layar
        setLocationRelativeTo(null); // Ketika compile, layar akan ditampilkan di tengah
        setVisible(true); // Menampilkan layar
        setBackground(new Color(0, 0, 0, 0)); // mengubah komponen paint menjadi warna putih
        setLayout(null); 
        
        // Add intro music
        introMusic.start();

        //Index 0: Cool-Tobu (Game Whack a Moles)
        trackList.add(new Track("Cool Start Image.png", "Cool Game Image.png",
                "Cool-Tobu Selected.mp3", "Cool-Tobu.mp3"));

        //Exit button
        //Ingat bahwa tombol exit harus dideklarasikan sebelum menu bar agar ditempatkan di atas menu bar
        add(exitButton);
        exitButton.setBounds(1245, 0, 30, 30); //Menu bar di ujung layar
        exitButton.setBorderPainted(false); //need to set JButton so that it fits our button image
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            
            //When mouse is on top of the exit button
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Mengubah kursor saat diarahkan ke tombol Exit 
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //Putar sekali
                buttonEnteredMusic.start();
            }
            
            //Mengembalikan bentuk kursor seperti semula
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonBasicImage);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            //Saat tombol Exit ditekan
            public void mousePressed(MouseEvent e) {
                System.exit(0); //exit the game
            }
        });

        //Tombol Start
        add(startButton);
        startButton.setBounds(200, 600, 400, 100);
        startButton.setBorderPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startButtonEnteredImage);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Mengubah kursor saat diarahkan ke tombol Start 
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //Putar sekali
                buttonEnteredMusic.start();
            }
            
            //Mengembalikan bentuk kursor seperti semula
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(startButtonBasicImage);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            //Saat tombol Start ditekan
            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
                //Masuk
                enterMain();
            }
        });

        //Tombol Quit
        add(quitButton);
        quitButton.setBounds(700, 600, 400, 100);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            //@Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(quitButtonEnteredImage);
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Mengubah kursor saat diarahkan ke tombol Start 
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //Putar sekali
                buttonEnteredMusic.start();
            }
            //Mengembalikan bentuk kursor seperti semula

            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(quitButtonBasicImage);
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            //Saat tombol Exit ditekan

            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //Putar sekali
                buttonEnteredMusic.start();
                System.exit(0); //exit the game
            }
        });
        
        //Easy Button
        add(easyButton);
        easyButton.setVisible(false);
        easyButton.setBounds(200, 580, 250, 67);
        easyButton.setBorderPainted(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setFocusPainted(false);
        easyButton.addMouseListener(new MouseAdapter() {
            //@Override
            public void mouseEntered(MouseEvent e) {
                easyButton.setIcon(easyButtonEnteredImage);
                easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
            }

            public void mouseExited(MouseEvent e) {
                easyButton.setIcon(easyButtonBasicImage);
                easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
                //Easy Button Event
                gameStart(nowSelected, "easy");

            }
        });
        
        //Normal Button
        normalButton.setVisible(false);
        normalButton.setBounds(500, 580, 250, 67);
        normalButton.setBorderPainted(false);
        normalButton.setContentAreaFilled(false);
        normalButton.setFocusPainted(false);
        normalButton.addMouseListener(new MouseAdapter() {
            //@Override
            public void mouseEntered(MouseEvent e) {
                normalButton.setIcon(normalButtonEnteredImage);
                normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
            }

            public void mouseExited(MouseEvent e) {
                normalButton.setIcon(normalButtonBasicImage);
                normalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e) {    
                Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
//                //Normal Button Event
                gameStart(nowSelected, "normal");

            }
        });
        add(normalButton);

        //Hard Button
        hardButton.setVisible(false);
        hardButton.setBounds(800, 580, 250, 67);
        hardButton.setBorderPainted(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setFocusPainted(false);
        hardButton.addMouseListener(new MouseAdapter() {
            //@Override
            public void mouseEntered(MouseEvent e) {
                hardButton.setIcon(hardButtonEnteredImage);
                hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
            }

            public void mouseExited(MouseEvent e) {
                hardButton.setIcon(hardButtonBasicImage);
                hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
                //hardButton Event
                gameStart(nowSelected, "hard");

            }
        });
        add(hardButton);

        //Back Button
        backButton.setVisible(false);
        backButton.setBounds(20, 50, 60, 60);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            //@Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonEnteredImage);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
            }

            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonBasicImage);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
                buttonEnteredMusic.start();
                //Event going back to main screen
                backMain();

            }
        });
        add(backButton);

        //Menubar
        menuBar.setBounds(0, 0, 1280, 30); // declares position and size of menubar
        menuBar.addMouseListener(new MouseAdapter() {
//			@Override
            public void mousePressed(MouseEvent e) { //when a mouse event occurs, retrieve the x and y coordinates of the mouse
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            //@Override
            public void mouseDragged(MouseEvent e) { // whenever mouse is dragged, get x,y position of the mouse and move the screen accordingly
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });
        add(menuBar); // adds menubar to jframe

        content.setVisible(false);
        content.setBackground(new Color(0, 102, 0));
        content.setBounds(32, 150, 525, 525);
        content.setLayout(null);
        content.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                
        //Memasang gambar palu
        loadImage("../images/hammer.png").getImage(),
        new Point(0,0),"custom cursor1")
        );
        
        add(contentList);
        contentList.setBackground(new Color(0, 102, 0));
        contentList.setVisible(false);
        contentList.setBounds(600, 150, 140, 200);
        contentList.setLayout(null);

        // Memasang lubang tikus
        holes[0] = new JLabel("0");
        holes[0].setName("0");
        holes[0].setBounds(0, 396, 132, 132);
        content.add(holes[0]);

        holes[1] = new JLabel("1");
        holes[1].setName("1");
        holes[1].setBounds(132, 396, 132, 132);
        content.add(holes[1]);

        holes[2] = new JLabel("2");
        holes[2].setName("2");
        holes[2].setBounds(264, 396, 132, 132);
        content.add(holes[2]);

        holes[3] = new JLabel("3");
        holes[3].setName("3");
        holes[3].setBounds(396, 396, 132, 132);
        content.add(holes[3]);

        holes[4] = new JLabel("4");
        holes[4].setName("4");
        holes[4].setBounds(0, 264, 132, 132);
        content.add(holes[4]);

        holes[5] = new JLabel("5");
        holes[5].setName("5");
        holes[5].setBounds(132, 264, 132, 132);
        content.add(holes[5]);

        holes[6] = new JLabel("6");
        holes[6].setName("6");
        holes[6].setBounds(264, 264, 132, 132);
        content.add(holes[6]);

        holes[7] = new JLabel("7");
        holes[7].setName("7");
        holes[7].setBounds(396, 264, 132, 132);
        content.add(holes[7]);

        holes[8] = new JLabel("8");
        holes[8].setName("8");
        holes[8].setBounds(0, 132, 132, 132);
        content.add(holes[8]);

        holes[9] = new JLabel("9");
        holes[9].setName("9");
        holes[9].setBounds(132, 132, 132, 132);
        content.add(holes[9]);

        holes[10] = new JLabel("10");
        holes[10].setName("10");
        holes[10].setBounds(264, 132, 132, 132);
        content.add(holes[10]);

        holes[11] = new JLabel("11");
        holes[11].setName("11");
        holes[11].setBounds(396, 132, 132, 132);
        content.add(holes[11]);

        holes[12] = new JLabel("12");
        holes[12].setName("12");
        holes[12].setBounds(0, 0, 132, 132);
        content.add(holes[12]);

        holes[13] = new JLabel("13");
        holes[13].setName("13");
        holes[13].setBounds(132, 0, 132, 132);
        content.add(holes[13]);

        holes[14] = new JLabel("14");
        holes[14].setName("14");
        holes[14].setBounds(264, 0, 132, 132);
        content.add(holes[14]);

        holes[15] = new JLabel("15");
        holes[15].setName("15");
        holes[15].setBounds(396, 0, 132, 132);
        content.add(holes[15]);
        add(content);
//
        lblScore = new JLabel("Score: 0");
        lblScore.setVisible(false);
        lblScore.setHorizontalAlignment(SwingConstants.TRAILING);
        lblScore.setFont(new Font("Cambria", Font.BOLD, 14));
        lblScore.setForeground(new Color(135, 206, 250));
        lblScore.setBounds(417, 80, 144, 33);
        add(lblScore);
//
        lblTimeLeft = new JLabel("0");
        lblTimeLeft.setVisible(false);
        lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimeLeft.setForeground(new Color(240, 128, 128));
        lblTimeLeft.setFont(new Font("Cambria Math", Font.BOLD, 24));
        lblTimeLeft.setBounds(232, 80, 144, 33);
        add(lblTimeLeft);
//
        lblHighscore = new JLabel("Highscore");
        lblHighscore.setVisible(false);
        lblHighscore.setHorizontalAlignment(SwingConstants.TRAILING);
        lblHighscore.setForeground(new Color(255, 255, 0));
        lblHighscore.setFont(new Font("Cambria", Font.BOLD, 14));
        lblHighscore.setBounds(528, 100, 134, 33);
        add(lblHighscore);
//        
        leada = new JList(listName.toArray());
        leada.setVisible(false);
        leada.setSize(100, 500);
        leada.setBounds(0, 0, 90, 400);
        contentList.add(leada);
//        
        leadb = new JList(listBoard.toArray());
        leadb.setVisible(false);
        leadb.setSize(100, 500);
        leadb.setBounds(90, 0, 50, 400);
        contentList.add(leadb);
//
        btnStart = new JButton("Start");
        btnStart.setBackground(Color.WHITE);
        btnStart.setBounds(250, 110, 110, 33);
        btnStart.setVisible(false);
        add(btnStart);
//        
        nametext = new JTextField("nama");
        nametext.setVisible(false);
        nametext.setBackground(Color.WHITE);
        nametext.setBounds(480, 120, 80, 20);
        add(nametext);
//
//        setContentPane(content);
    }

    // Methods
    // 1) Paint method
    // Extend JFrame class to create your own frame class so that you can override the paint() method.
    // The paint() method provides you a Graphics object, which will give you utility methods to draw various types of graphics.
    // The paint() method is inherited by JFrame class from the Component class. It
    // will be called whenever this component should be painted.
    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // create image by the screen size
        screenGraphic = screenImage.getGraphics();
        screenDraw(screenGraphic); // draw the image we want on the screen
        g.drawImage(screenImage, 0, 0, null); // draw screenImage at point (0,0)
    }

    // 2) screenDraw method
    // There are two ways to draw: drawimage and paintcomponents
    public void screenDraw(Graphics g) {
        g.drawImage(background, 0, 0, null); //generally, we use drawImage to draw moving images
        if (isMainScreen) {
            g.drawImage(startImage, 340, 70, null);
        }
        paintComponents(g); // draws the images in the screen image (menubar stays constant; doesn't change. therefore use paintcomponent not drawimage), draws all the "add()" components
        this.repaint(); // calling the paint method again
        // Way this works: the computer paints the screen at every moment until the
        // program terminates
    }

    //function for Track
    //input the # of the currently selected track
    public void selectTrack(int nowSelected) {
        if (selectedMusic != null) {
            selectedMusic.close();
        }
        //change titleImage and selectedImage to the one that corresponds to the selected track
        startImage = new ImageIcon(Main.class.getResource("/images/" + trackList.get(nowSelected).getStartImage())).getImage(); //get value of the TitleImage
        selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
        selectedMusic.start();
    }
    
    // Menampilkan data top skor
    public void getListData () {
        try {        
            leada.setVisible(true);        
            leadb.setVisible(true);
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wam",     "root", ""); // Koneksi ke database
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM scores order by hasil DESC LIMIT 10"); // Meampilkan data maksimal 10
            ResultSet rs = st.executeQuery(sql);
            ArrayList<String> hasil = new ArrayList<String>();
            ArrayList<String> name = new ArrayList<String>();
            while(rs.next()) { 
                name.add(rs.getString("name"));   
                hasil.add(rs.getString("hasil"));                      
                listBoard.add(rs.getString("hasil"));                    
                listName.add(rs.getString("name"));
            }
            leada.setListData(name.toArray());
            leada = new JList(name.toArray());
            leadb.setListData(hasil.toArray());
            leadb = new JList(hasil.toArray());
        } catch(Exception ex){
            leada.setVisible(false);        
            leadb.setVisible(false);
        } 
        return;
    }

    //Layar utama
    public void gameStart(int nowSelected, String difficuly) {
        if (selectedMusic != null) {
            selectedMusic.close();
        }
        isVisible = true;
        if (isVisible) {
            contentList.setVisible(true);
            getListData();
        }
        clearBoard();
        
        // Waktu berdasarkan level
        if (difficuly == "easy") {
            initEvents(30); // 30 detik
        } else if (difficuly == "normal") {
            initEvents(20); // 20 detik
        } else {
            initEvents(10);  // 10 detik
        }
        isMainScreen = false;
        easyButton.setVisible(false);   
        normalButton.setVisible(false);
        hardButton.setVisible(false);
        //background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
        backButton.setVisible(true);     
        content.setVisible(true);
        lblScore.setVisible(true);
        lblTimeLeft.setVisible(true);
        lblHighscore.setVisible(true);
        btnStart.setVisible(true);
        nametext.setVisible(true);
    }

    //Fungsi tombol kembali ke layar utama
    public void backMain() {
        isMainScreen = true;
        easyButton.setVisible(true);
        normalButton.setVisible(true);
        hardButton.setVisible(true);
        background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
        backButton.setVisible(false);
        selectTrack(nowSelected);
        backButton.setVisible(false);     
        content.setVisible(false);
        lblScore.setVisible(false);
        lblTimeLeft.setVisible(false);
        lblHighscore.setVisible(false);
        btnStart.setVisible(false);
        nametext.setVisible(false);
        isVisible = false;
        contentList.setVisible(false);
    }

    //Layar game whack a mole
    public void enterMain() {
        startButton.setVisible(false);
        quitButton.setVisible(false);
        background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
        isMainScreen = true;
        easyButton.setVisible(true);
        normalButton.setVisible(true);
        hardButton.setVisible(true);
        introMusic.close();
        selectTrack(0);
    }
    
    // Memasang path agar dapat disesuaikan dengan folder gambar
    private ImageIcon loadImage(String path){
        Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
        Image scaledImage = image.getScaledInstance(132, 132,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // Game over
    private void gameOver(){
        btnStart.setEnabled(true);
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wam",     "root", ""); //Koneksi ke database
            Statement state=(Statement) con.createStatement();
            String name=nametext.getText();
            //generate unique id
            String id=UUID.randomUUID().toString();
            int hasil=score;
            String insert = "INSERT INTO scores VALUES ('"+ id +"', '"  + name +"',"+ hasil +")"; //Insert data ke tabel score
            state.executeUpdate(insert); //Eksekusi syntax
        } catch(Exception ex){}
        if(score > highscore){
            highscore = score;
            lblHighscore.setText("Highscore: " + highscore);
            JOptionPane.showMessageDialog(this, "Your final score is: " + score, "You beat the high score!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Your final score is: " + score, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        }
        score = 0;
        timeLeft = 0;
        lblScore.setText("Score: 0");
        lblTimeLeft.setText("0");
        clearBoard();
    }

    private void pressedButton(int id){
        int val = board[id];

        //if val is 1 = mole
        //if val is 0 = empty hole
        if(val==1){ 
            score++;
        }else{ //val==0
            score--;
        }
        System.out.println(val);
        lblScore.setText("Score: " + score); //update the score
        clearBoard();
        genRandMole();
    }

    private void initEvents(int time){
        timeLeft = time;
        lblTimeLeft.setText(""+time);
        for(int i = 0; i < holes.length; i++){
            holes[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    JLabel lbl = (JLabel)e.getSource();
                    int id = Integer.parseInt(lbl.getName());
                    pressedButton(id);
                }
            });
        }
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearBoard();
                genRandMole();
                timer.start();    
            }
        });

        // Waktu game
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Kalo waktu abis, game over
                if(timeLeft == 0){
                    lblTimeLeft.setText("" + timeLeft);
                    timer.stop();
                    gameOver();
                }
                lblTimeLeft.setText("" + timeLeft);
                timeLeft--; //Sisa waktu
            }
        });
    }
    
    // Tikus masuk tanah
    private void clearBoard(){
        for(int i = 0; i < 16; i++){
            holes[i].setIcon(loadImage("../images/moleIn.png"));
            board[i] = 0;
        }
    }

    // Tikus muncul secara acak
    private void genRandMole(){
        Random rnd = new Random(System.currentTimeMillis()); //Menampilkan gambar dengan secara acak sesuai dengan waktu
        int moleID = rnd.nextInt(16);
        board[moleID] = 1;
        holes[moleID].setIcon(loadImage("../images/moleOut.png"));
    }
}
