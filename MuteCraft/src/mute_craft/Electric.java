package mute_craft;

import javax.swing.*;
import java.awt.*;

public class Electric extends JFrame {
    public Electric() {
        // JFrame 설정
        setTitle("Electric Guitar");
        setBounds(100, 100, 867, 393);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Content Pane 설정
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        // 이전 버튼
        JButton previousButton = createTransparentButton(new ImageIcon("images/icon_previous.png"));
        previousButton.setBounds(10, 5, 30, 30);
        contentPane.add(previousButton);

        // 추가 버튼
        JButton addButton = createTransparentButton(new ImageIcon("images/icon_add.png"));
        addButton.setBounds(795, 5, 30, 30);
        contentPane.add(addButton);

        // Start, Stop, Record, Metronome 버튼 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(336, 0, 200, 50); // 버튼들을 가운데 배치
        centerPanel.setLayout(new GridLayout(1, 4, 10, 0)); // 1행 4열 그리드
        centerPanel.setOpaque(false); // 패널 배경 투명 처리

        JButton startButton = createTransparentButton(new ImageIcon("images/icon_start.png"));
        JButton stopButton = createTransparentButton(new ImageIcon("images/icon_stop.png"));
        JButton recordButton = createTransparentButton(new ImageIcon("images/icon_record.png"));
        JButton metronomeButton = createTransparentButton(new ImageIcon("images/icon_metronome.png"));

        centerPanel.add(startButton);
        centerPanel.add(stopButton);
        centerPanel.add(recordButton);
        centerPanel.add(metronomeButton);
        contentPane.add(centerPanel);

        // 코드 버튼 추가
        addCodeButton(contentPane, "Em", new ImageIcon("images/icon_Em2.png"), 115, 97);
        addCodeButton(contentPane, "Am", new ImageIcon("images/icon_Am2.png"), 228, 91);
        addCodeButton(contentPane, "Dm", new ImageIcon("images/icon_Dm2.png"), 331, 85);
        addCodeButton(contentPane, "G", new ImageIcon("images/icon_G2.png"), 430, 79);
        addCodeButton(contentPane, "C", new ImageIcon("images/icon_C2.png"), 522, 75);
        addCodeButton(contentPane, "F", new ImageIcon("images/icon_F2.png"), 614, 69);
        addCodeButton(contentPane, "Bb", new ImageIcon("images/icon_Bb2.png"), 695, 65);
        addCodeButton(contentPane, "Bdim", new ImageIcon("images/icon_Bdim2.png"), 770, 61);

        // 기타 이미지
        ImageIcon guitarIcon = new ImageIcon("images/electric_guitar.png");
        JLabel guitarLabel = new JLabel(updateImageSize(guitarIcon, 852, 291));
        guitarLabel.setBounds(0, 100, 852, 291);
        contentPane.add(guitarLabel);

        // 기본 창 표시
        setVisible(true);
    }

    // 이미지 사이즈 변경
    private ImageIcon updateImageSize(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image update = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(update);
    }

    // 투명 버튼 생성
    private JButton createTransparentButton(ImageIcon icon) {
        JButton button = new JButton(updateImageSize(icon, 25, 25));
        button.setContentAreaFilled(false); // 버튼 배경 채우기 비활성화
        button.setBorderPainted(false); // 버튼 테두리 비활성화
        button.setFocusPainted(false); // 포커스 테두리 비활성화
        button.setOpaque(false); // 버튼 배경 투명화
        return button;
    }

    // 코드 버튼 추가 메서드
    private void addCodeButton(Container contentPane, String codeName, ImageIcon icon, int x, int width) {
        // 코드 버튼
        JButton codeButton = new JButton(updateImageSize(icon, width, 336));
        codeButton.setBounds(x, 55, width, 336);
        codeButton.setContentAreaFilled(false); // 배경 투명화
        codeButton.setBorderPainted(false); // 테두리 비활성화
        codeButton.setFocusPainted(false); // 포커스 테두리 비활성화
        codeButton.setOpaque(false); // 배경 투명화
        contentPane.add(codeButton);

        // 코드 이름 텍스트
        JLabel codeTextLabel = new JLabel(codeName, SwingConstants.CENTER);
        codeTextLabel.setFont(new Font("Arial", Font.BOLD, 23)); // 텍스트 폰트 설정
        codeTextLabel.setForeground(Color.BLACK); // 텍스트 색상 설정
        codeTextLabel.setBounds(x, 68, width, 20); // 텍스트 위치 및 크기 설정
        contentPane.add(codeTextLabel);
    }

    public static void main(String[] args) {
        new Electric();
    }
}
