import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

public class P01 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    P01 frame = new P01();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public P01() {
        setType(Type.UTILITY);
        setTitle("Piano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1476, 716);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 이미지 아이콘 설정
        ImageIcon keyboard = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\keyboard.png");
        ImageIcon button_back = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\button_back.png");
        ImageIcon button_play = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\button_play.png");
        ImageIcon button_stop = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\button_stop.png");
        ImageIcon button_record = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\button_record.png");
        ImageIcon metronome = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\metronome.png");
        ImageIcon button_add = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\button_add.png");

        // panel_buttons (버튼을 담을 패널)
        JPanel panel_buttons = new JPanel();
        panel_buttons.setBounds(0, 0, 1476, 100);
        panel_buttons.setBackground(Color.WHITE);
        panel_buttons.setLayout(new BorderLayout());

        JButton leftButton = new JButton("");
        JButton rightButton = new JButton("");
        JLabel leftLabel = new JLabel("Piano");
        leftLabel.setFont(new Font("Serif", Font.PLAIN, 40));

        // 왼쪽 패널
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(leftButton);
        leftPanel.add(leftLabel);

        // 중앙 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        JButton button1 = new JButton("");
        JButton button2 = new JButton("");
        JButton button3 = new JButton("");
        JButton button4 = new JButton("");
        centerPanel.add(button1);
        centerPanel.add(button2);
        centerPanel.add(button3);
        centerPanel.add(button4);

        // 오른쪽 패널
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(rightButton);

        // 이미지 버튼 설정
        leftButton.setIcon(button_back);
        leftButton.setContentAreaFilled(false);
        leftButton.setBorderPainted(false);
        leftButton.setFocusPainted(false);

        button1.setIcon(button_play);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);

        button2.setIcon(button_stop);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);

        button3.setIcon(button_record);
        button3.setContentAreaFilled(false);
        button3.setBorderPainted(false);
        button3.setFocusPainted(false);

        button4.setIcon(metronome);
        button4.setContentAreaFilled(false);
        button4.setBorderPainted(false);
        button4.setFocusPainted(false);
        
        rightButton.setIcon(button_add);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);
        rightButton.setFocusPainted(false);

        // 패널에 추가
        panel_buttons.add(leftPanel, BorderLayout.WEST);
        panel_buttons.add(centerPanel, BorderLayout.CENTER);
        panel_buttons.add(rightPanel, BorderLayout.EAST);
        contentPane.add(panel_buttons);


        // panel_notes (버튼을 담을 패널)
        JPanel panel_notes_white = new JPanel();
        panel_notes_white.setBounds(0, 400, 1476, 300); // 패널 위치와 크기 설정
        panel_notes_white.setLayout(new GridLayout(2, 14, 0, 0));  // 14개의 버튼, 2줄로 배치, 간격 0px
        panel_notes_white.setBackground(null);  // 배경을 투명하게 설정
        panel_notes_white.setOpaque(false);    // 패널도 투명하게 설정
        contentPane.add(panel_notes_white);    // contentPane에 패널을 추가

        // 1번부터 28번까지 버튼을 동적으로 생성하여 이미지 로드
        for (int i = 1; i <= 28; i++) {
            JButton button = new JButton();
            String imagePath = "C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\notes\\notes_" + String.format("%02d", i) + ".png";

            // 이미지 아이콘을 버튼에 추가
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                ImageIcon noteIcon = new ImageIcon(imagePath);

                // 이미지를 버튼 크기에 맞게 리사이즈
                ImageIcon resizedIcon = new ImageIcon(noteIcon.getImage().getScaledInstance(80, 76, Image.SCALE_SMOOTH));
                button.setIcon(resizedIcon);

                // 버튼의 텍스트를 없애고 이미지만 표시되게 설정
                button.setText("");  // 텍스트 없애기
                button.setContentAreaFilled(false); // 버튼 배경을 없앰
                button.setBorderPainted(false);     // 버튼 테두리 없애기
                button.setPreferredSize(new java.awt.Dimension(80, 76));  // 버튼 크기 설정 (이미지 크기에 맞게 조정)
                
                final int buttonIndex = i;  // 버튼 인덱스를 final로 처리
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("btn" + buttonIndex + "버튼클릭!"); // 버튼 번호 출력
                    }
                });

                panel_notes_white.add(button);
            } else {
                System.out.println("이미지 파일이 존재하지 않습니다: " + imagePath);
            }
        }
        
        JPanel panel_notes_black = new JPanel();
        panel_notes_black.setBounds(0, 200, 1476, 322);
        panel_notes_black.setLayout(null);
        panel_notes_black.setBackground(null);
        panel_notes_black.setOpaque(false);
        contentPane.add(panel_notes_black);
        
        ImageIcon c = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\notes\\notes_c#.png");
        ImageIcon e = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\notes\\notes_eb.png");
        ImageIcon f = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\notes\\notes_f#.png");
        ImageIcon a = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\notes\\notes_ab.png");
        ImageIcon b = new ImageIcon("C:\\HSU\\그 외\\3학년\\네트워크프로그래밍\\프로젝트\\img\\notes\\notes_bb.png");

        Image imgC = c.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconC = new ImageIcon(imgC);
        Image imgE = e.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconE = new ImageIcon(imgE);
        Image imgF = f.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconF = new ImageIcon(imgF);
        Image imgA = a.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconA = new ImageIcon(imgA);
        Image imgB = b.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconB = new ImageIcon(imgB);
        
       
        // 버튼 생성 및 이미지 설정
        JButton btn_c_sharp02 = new JButton();
        btn_c_sharp02.setIcon(resizedIconC);  // 버튼에 리사이즈된 이미지 아이콘 설정
        btn_c_sharp02.setBounds(60, 50, 50, 50);  // 버튼 위치 및 크기 설정
        btn_c_sharp02.setContentAreaFilled(false);
        btn_c_sharp02.setBorderPainted(false);
        panel_notes_black.add(btn_c_sharp02);

        JButton btn_e_flat02 = new JButton();
        btn_e_flat02.setIcon(resizedIconE);
        btn_e_flat02.setBounds(180, 50, 50, 50);
        btn_e_flat02.setContentAreaFilled(false);
        btn_e_flat02.setBorderPainted(false);
        panel_notes_black.add(btn_e_flat02);

        JButton btn_f_sharp02 = new JButton();
        btn_f_sharp02.setIcon(resizedIconF);
        btn_f_sharp02.setBounds(380, 50, 50, 50);
        btn_f_sharp02.setContentAreaFilled(false);
        btn_f_sharp02.setBorderPainted(false);
        panel_notes_black.add(btn_f_sharp02);

        JButton btn_a_flat02 = new JButton();
        btn_a_flat02.setIcon(resizedIconA);
        btn_a_flat02.setBounds(500, 50, 50, 50);
        btn_a_flat02.setContentAreaFilled(false);
        btn_a_flat02.setBorderPainted(false);
        panel_notes_black.add(btn_a_flat02);

        JButton btn_b_flat02 = new JButton();
        btn_b_flat02.setIcon(resizedIconB);
        btn_b_flat02.setBounds(615, 50, 50, 50);
        btn_b_flat02.setContentAreaFilled(false);
        btn_b_flat02.setBorderPainted(false);
        panel_notes_black.add(btn_b_flat02);

        JButton btn_c_sharp03 = new JButton();
        btn_c_sharp03.setIcon(resizedIconC);
        btn_c_sharp03.setBounds(815, 50, 50, 50);
        btn_c_sharp03.setContentAreaFilled(false);
        btn_c_sharp03.setBorderPainted(false);
        panel_notes_black.add(btn_c_sharp03);

        JButton btn_e_flat03 = new JButton();
        btn_e_flat03.setIcon(resizedIconE);
        btn_e_flat03.setBounds(935, 50, 50, 50);
        btn_e_flat03.setContentAreaFilled(false);
        btn_e_flat03.setBorderPainted(false);
        panel_notes_black.add(btn_e_flat03);

        JButton btn_f_sharp03 = new JButton();
        btn_f_sharp03.setIcon(resizedIconF);
        btn_f_sharp03.setBounds(1135, 50, 50, 50);
        btn_f_sharp03.setContentAreaFilled(false);
        btn_f_sharp03.setBorderPainted(false);
        panel_notes_black.add(btn_f_sharp03);

        JButton btn_a_flat03 = new JButton();
        btn_a_flat03.setIcon(resizedIconA);
        btn_a_flat03.setBounds(1255, 50, 50, 50);
        btn_a_flat03.setContentAreaFilled(false);
        btn_a_flat03.setBorderPainted(false);
        panel_notes_black.add(btn_a_flat03);

        JButton btn_b_flat03 = new JButton();
        btn_b_flat03.setIcon(resizedIconB);
        btn_b_flat03.setBounds(1370, 50, 50, 50);
        btn_b_flat03.setContentAreaFilled(false);
        btn_b_flat03.setBorderPainted(false);
        panel_notes_black.add(btn_b_flat03);
        
    
        // panel_keyboard (배경 이미지용 패널
        JPanel panel_keyboard = new JPanel();
		panel_keyboard.setBounds(0, 100, 1476, 616); contentPane.add(panel_keyboard);
		 
		double aspectRatio = 656.0 / 1704.0; int newHeight = (int) (1476 * aspectRatio);
		 
		Image scaledImage = keyboard.getImage().getScaledInstance(1476, newHeight,
		Image.SCALE_SMOOTH); ImageIcon scaledIcon = new ImageIcon(scaledImage);
		 
		JLabel label = new JLabel(scaledIcon); label.setBounds(0, 0, 1476,
		newHeight); panel_keyboard.add(label); 
    }
}