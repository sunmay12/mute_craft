import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Mixing extends JFrame {
    private Clip clip1, clip2;  // 오디오 클립
    private boolean isPlaying = false;  // 오디오 재생 여부

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // 이미지 아이콘 리사이즈 메서드
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public Mixing() {
        setTitle("Mixing Audio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 852, 393);  // 창 크기 설정

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());  // 전체 프레임을 BorderLayout으로 설정

        // panel_buttons (버튼을 담을 패널)
        JPanel panel_buttons = new JPanel();
        panel_buttons.setLayout(new BorderLayout());
        panel_buttons.setPreferredSize(new Dimension(852, 50));  // 상단 버튼 영역 크기 설정

        // 이미지 아이콘 설정
        ImageIcon button_back = new ImageIcon(getClass().getResource("/img/button_back.png"));
        ImageIcon button_play = new ImageIcon(getClass().getResource("/img/button_play.png"));
        ImageIcon button_stop = new ImageIcon(getClass().getResource("/img/button_stop.png"));
        ImageIcon button_record = new ImageIcon(getClass().getResource("/img/button_record.png"));
        ImageIcon metronome = new ImageIcon(getClass().getResource("/img/metronome.png"));

        JButton backbtn = new JButton("");
        JButton addbtn = new JButton("");
        JLabel leftLabel = new JLabel("Mixing");
        leftLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        // 왼쪽 패널
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(backbtn);
        leftPanel.add(leftLabel);

        // 중앙 패널 (재생/멈춤 버튼)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        JButton playbtn = new JButton("");
        JButton stopbtn = new JButton("");
        JButton recordbtn = new JButton("");
        JButton metronomebtn = new JButton("");
        centerPanel.add(playbtn);
        centerPanel.add(stopbtn);
        centerPanel.add(recordbtn);
        centerPanel.add(metronomebtn);

        // 이미지 버튼 설정
        backbtn.setIcon(resizeIcon(button_back, 25, 25));
        backbtn.setContentAreaFilled(false);
        backbtn.setBorderPainted(false);
        backbtn.setFocusPainted(false);
        backbtn.setPreferredSize(new Dimension(50, 50));

        playbtn.setIcon(resizeIcon(button_play, 25, 25));
        playbtn.setContentAreaFilled(false);
        playbtn.setBorderPainted(false);
        playbtn.setFocusPainted(false);
        playbtn.setPreferredSize(new Dimension(50, 50));

        stopbtn.setIcon(resizeIcon(button_stop, 25, 25));
        stopbtn.setContentAreaFilled(false);
        stopbtn.setBorderPainted(false);
        stopbtn.setFocusPainted(false);
        stopbtn.setPreferredSize(new Dimension(50, 50));

        recordbtn.setIcon(resizeIcon(button_record, 25, 25));
        recordbtn.setContentAreaFilled(false);
        recordbtn.setBorderPainted(false);
        recordbtn.setFocusPainted(false);
        recordbtn.setPreferredSize(new Dimension(50, 50));

        metronomebtn.setIcon(resizeIcon(metronome, 25, 25));
        metronomebtn.setContentAreaFilled(false);
        metronomebtn.setBorderPainted(false);
        metronomebtn.setFocusPainted(false);
        metronomebtn.setPreferredSize(new Dimension(50, 50));

        // 패널에 추가
        panel_buttons.add(leftPanel, BorderLayout.WEST);
        panel_buttons.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(panel_buttons, BorderLayout.NORTH);  // 상단에 추가

        // 오디오 파일 준비
        try {
            File audioFile1 = new File("src/resources/lydfiler/audio/Material Girl.wav");
            File audioFile2 = new File("src/resources/lydfiler/audio/Last Christmas.wav");

            AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(audioFile1);
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);

            clip1 = AudioSystem.getClip();
            clip1.open(audioStream1);

            clip2 = AudioSystem.getClip();
            clip2.open(audioStream2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        // 재생 버튼 기능
        playbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaying) {
                    playAudio();  // 오디오 재생
                    isPlaying = true;
                }
            }
        });

        // 멈춤 버튼 기능
        stopbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAudio();  // 오디오 멈춤
                isPlaying = false;
            }
        });

        // 첫 번째 오디오 재생 버튼
        JButton btnPlay1 = new JButton("Material Girl");
        btnPlay1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSingleAudio(1);  // 첫 번째 오디오만 재생
            }
        });
       // btnPlay1.setPreferredSize(new Dimension(200, 100));  // 크기 설정
        btnPlay1.setMaximumSize(new Dimension(200, 100)); 
        btnPlay1.setBackground(Color.pink);
        btnPlay1.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 가로 정렬
        btnPlay1.setVerticalAlignment(SwingConstants.CENTER);   // 텍스트 세로 정렬
        btnPlay1.setFont(new Font("Arial", Font.PLAIN, 14));  // 글씨 크기 설정
        
        // 두 번째 오디오 재생 버튼
        JButton btnPlay2 = new JButton("Last Christmas");
        btnPlay2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSingleAudio(2);  // 두 번째 오디오만 재생
            }
        });
       // btnPlay2.setPreferredSize(new Dimension(200, 100));  // 크기 설정
        btnPlay2.setMaximumSize(new Dimension(200, 100)); 
        btnPlay2.setBackground(Color.pink);
        btnPlay2.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 가로 정렬
        btnPlay2.setVerticalAlignment(SwingConstants.CENTER);   // 텍스트 세로 정렬
        btnPlay2.setFont(new Font("Arial", Font.PLAIN, 14));  // 글씨 크기 설정
        
        // 버튼들을 BoxLayout으로 세로로 정렬
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // 세로로 배치
        buttonPanel.add(Box.createVerticalStrut(30)); 
        buttonPanel.add(btnPlay1);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(btnPlay2);

        contentPane.add(buttonPanel, BorderLayout.WEST);  // 왼쪽에 배치
    }

    // 두 개의 오디오 파일을 모두 재생하는 메서드
    private void playAudio() {
        clip1.setFramePosition(0);  // 첫 번째 오디오 초기화
        clip1.start();  // 첫 번째 오디오 시작

        clip2.setFramePosition(0);  // 두 번째 오디오 초기화
        clip2.start();  // 두 번째 오디오 시작
    }

    // 선택한 오디오 하나만 재생하는 메서드
    private void playSingleAudio(int audioNumber) {
        if (audioNumber == 1) {
            clip1.setFramePosition(0);  // 첫 번째 오디오 초기화
            clip1.start();  // 첫 번째 오디오 시작
        } else if (audioNumber == 2) {
            clip2.setFramePosition(0);  // 두 번째 오디오 초기화
            clip2.start();  // 두 번째 오디오 시작
        }
    }

    // 오디오 멈추기 메서드
    private void stopAudio() {
        if (clip1.isRunning()) {
            clip1.stop();  // 첫 번째 오디오 멈추기
        }
        if (clip2.isRunning()) {
            clip2.stop();  // 두 번째 오디오 멈추기
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Mixing frame = new Mixing();
                frame.setVisible(true);
            }
        });
    }
}
