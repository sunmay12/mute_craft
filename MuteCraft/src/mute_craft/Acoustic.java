package mute_craft;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class Acoustic extends JFrame {
    private ArrayList<String> soundSequence = new ArrayList<>(); // 녹음 중 저장된 사운드 파일 경로 리스트
    private ArrayList<File> recordedFiles = new ArrayList<>();   // 녹음 완료 후 저장된 파일 리스트
    private boolean isRecording = false;                        // 녹음 상태 여부
    private File recordedFile = new File("recorded.wav");        // 임시 녹음 파일

    public Acoustic() {
        // JFrame 기본 설정
        setTitle("Acoustic Guitar");
        setBounds(100, 100, 867, 393); // 창 위치 및 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨테이너 설정
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        // 이전 버튼 추가
        JButton previousButton = createTransparentButton(new ImageIcon("images/icon_previous.png"));
        previousButton.setBounds(10, 5, 30, 30);
        contentPane.add(previousButton);

        // 추가 버튼 추가
        JButton addButton = createTransparentButton(new ImageIcon("images/icon_add.png"));
        addButton.setBounds(795, 5, 30, 30);
        contentPane.add(addButton);

        // 중앙 컨트롤 버튼 패널 생성
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(336, 0, 200, 50);
        centerPanel.setLayout(new GridLayout(1, 4, 10, 0));
        centerPanel.setOpaque(false);

        // 각 버튼 생성 및 리스너 설정
        JButton startButton = createTransparentButton(new ImageIcon("images/icon_start.png"));
        JButton stopButton = createTransparentButton(new ImageIcon("images/icon_stop.png"));
        JButton recordButton = createTransparentButton(new ImageIcon("images/icon_record.png"));
        JButton metronomeButton = createTransparentButton(new ImageIcon("images/icon_metronome.png"));

        recordButton.addActionListener(e -> startRecording());  // 녹음 시작
        stopButton.addActionListener(e -> stopRecording());     // 녹음 중지
        startButton.addActionListener(e -> playRecording());    // 최근 녹음 파일 재생

        centerPanel.add(startButton);
        centerPanel.add(stopButton);
        centerPanel.add(recordButton);
        centerPanel.add(metronomeButton);
        contentPane.add(centerPanel);

        // 코드 버튼 추가 (Em, Am, Dm 등)
        addCodeButton(contentPane, "Em", new ImageIcon("images/icon_Em.png"), "sounds/sound_Em.wav", 105, 100);
        addCodeButton(contentPane, "Am", new ImageIcon("images/icon_Am.png"), "sounds/sound_Am.wav", 219, 95);
        addCodeButton(contentPane, "Dm", new ImageIcon("images/icon_Dm.png"), "sounds/sound_Dm.wav", 327, 89);
        addCodeButton(contentPane, "G", new ImageIcon("images/icon_G.png"), "sounds/sound_G.wav", 429, 83);
        addCodeButton(contentPane, "C", new ImageIcon("images/icon_C.png"), "sounds/sound_C.wav", 522, 80);
        addCodeButton(contentPane, "F", new ImageIcon("images/icon_F.png"), "sounds/sound_F.wav", 614, 74);
        addCodeButton(contentPane, "Bb", new ImageIcon("images/icon_Bb.png"), "sounds/sound_Bb.wav", 698, 71);
        addCodeButton(contentPane, "Bdim", new ImageIcon("images/icon_Bdim.png"), "sounds/sound_Bdim.wav", 779, 68);

        // 기타 이미지 추가
        ImageIcon guitarIcon = new ImageIcon("images/acoustic_guitar.png");
        JLabel guitarLabel = new JLabel(updateImageSize(guitarIcon, 852, 291));
        guitarLabel.setBounds(0, 100, 852, 291);
        contentPane.add(guitarLabel);

        // 화면 표시
        setVisible(true);
    }
    
    // 녹음 시작: 기존 사운드 순서 초기화 및 녹음 상태 변경
    private void startRecording() {
        soundSequence.clear();
        isRecording = true;
        System.out.println("Recording started...");
    }

    // 녹음 중지: 녹음된 사운드 파일 병합 및 저장
    private void stopRecording() {
        isRecording = false;
        System.out.println("Recording stopped. Merging sounds...");

        try {
            if (soundSequence.isEmpty()) {
                System.out.println("No sounds to merge.");
                return;
            }

            // 고유 파일명 생성
            String timestamp = String.valueOf(System.currentTimeMillis());
            File recordedFile = new File("recorded_" + timestamp + ".wav");
            recordedFiles.add(recordedFile); // 파일 리스트에 추가

            // 사운드 파일 병합
            Vector<InputStream> inputStreams = new Vector<>();
            AudioFormat audioFormat = null;
            long totalFrames = 0;

            for (String soundPath : soundSequence) {
                File soundFile = new File(soundPath);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

                if (audioFormat == null) {
                    audioFormat = audioInputStream.getFormat(); // 첫 번째 파일의 AudioFormat 사용
                }

                inputStreams.add(audioInputStream);
                totalFrames += audioInputStream.getFrameLength();
            }

            // 병합된 스트림 생성 및 저장
            SequenceInputStream concatenatedStream = new SequenceInputStream(inputStreams.elements());
            AudioInputStream mergedStream = new AudioInputStream(concatenatedStream, audioFormat, totalFrames);
            AudioSystem.write(mergedStream, AudioFileFormat.Type.WAVE, recordedFile);

            System.out.println("Recording saved to " + recordedFile.getAbsolutePath());
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    // 최근 녹음된 파일 재생
    private void playRecording() {
        if (recordedFiles.isEmpty()) {
            System.out.println("No recordings available to play.");
            return;
        }

        File latestFile = recordedFiles.get(recordedFiles.size() - 1); // 제일 최근 파일
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(latestFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Playing recording: " + latestFile.getName());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    // 코드 버튼 추가 및 사운드 재생/녹음
    private void addCodeButton(Container contentPane, String codeName, ImageIcon icon, String soundFilePath, int x, int width) {
        JButton codeButton = new JButton(updateImageSize(icon, width, 336));
        codeButton.setBounds(x, 55, width, 336);
        codeButton.setContentAreaFilled(false);
        codeButton.setBorderPainted(false);
        codeButton.setFocusPainted(false);
        codeButton.setOpaque(false);
        codeButton.addActionListener(e -> playAndRecordSound(soundFilePath));
        contentPane.add(codeButton);

        JLabel codeTextLabel = new JLabel(codeName, SwingConstants.CENTER);
        codeTextLabel.setFont(new Font("Arial", Font.BOLD, 23));
        codeTextLabel.setForeground(Color.BLACK);
        codeTextLabel.setBounds(x, 68, width, 20);
        contentPane.add(codeTextLabel);
    }

    // 사운드 재생 및 녹음 중일 경우 순서 추가
    private void playAndRecordSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            if (isRecording) {
                soundSequence.add(soundFilePath);
                System.out.println("Added to sequence: " + soundFile.getName());
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    // 투명 버튼 생성
    private JButton createTransparentButton(ImageIcon icon) {
        JButton button = new JButton(updateImageSize(icon, 25, 25));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    // 이미지 크기 조정
    private ImageIcon updateImageSize(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image updatedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(updatedImage);
    }

    // 메인 메서드: 프로그램 실행
    public static void main(String[] args) {
        new Acoustic();
    }
}