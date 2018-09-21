package poo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;

public class Principal {
  private JPanel painelPrincipal;
  private JTextField jTFNome;
  private JFormattedTextField jFTFCpf;
  private JButton adicionarButton;
  private JRadioButton femininoRadioButton;
  private JRadioButton masculinoRadioButton;
  private JCheckBox manhaCheckBox;
  private JCheckBox tardeCheckBox;
  private JCheckBox noiteCheckBox;
  private JTable tableFuncionarios;
  private JComboBox jCBPermissoes;
  private JList jListPermissoes;
  private JButton excluirButton;
  private JButton limparButton;
  private JButton excluirLinhaButton;
  private JButton button1;


  /**
   * Essa classe apresenta um exemplo de como fazer aplicação Swing com o IntelliJ.<BR>
   * A classe está incompleta e caberia alguns ajustes para melhorar sua usabilidade. Por exemplo,
   * só deveria ser possível incluir um funcionário se todos os campos estiverem preenchidos.
   *
   */
  public Principal() {




    jCBPermissoes.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int itemSelecionado = jCBPermissoes.getSelectedIndex();
        if (itemSelecionado >= 0){
          String permissao = (String) jCBPermissoes.getItemAt(itemSelecionado);

          DefaultListModel<String> modeloLista = (DefaultListModel<String>) jListPermissoes.getModel();
          if (!modeloLista.contains(permissao)){
            modeloLista.addElement(permissao);
          }

          limparButton.setEnabled(true);
        }
      }
    });
    limparButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Limpando a lista de permissões já concedidas
        DefaultListModel<String> modeloLista = (DefaultListModel<String>) jListPermissoes.getModel();
        modeloLista.clear();
        jCBPermissoes.setSelectedIndex(-1);
      }
    });

    jListPermissoes.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        DefaultListModel<String> modeloLista = (DefaultListModel<String>) jListPermissoes.getModel();
        if (modeloLista.isEmpty()){
          excluirButton.setEnabled(false);
        }else if ( ! jListPermissoes.isSelectionEmpty()){
          excluirButton.setEnabled(true);
        }
      }
    });
    excluirButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DefaultListModel<String> modeloLista = (DefaultListModel<String>) jListPermissoes.getModel();

        jListPermissoes.getSelectedValuesList().forEach((item)->{ modeloLista.removeElement(item);});

        excluirButton.setEnabled(false);

        if (modeloLista.isEmpty()){
          limparButton.setEnabled(false);
        }

      }
    });
    adicionarButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DefaultTableModel modelo = (DefaultTableModel) tableFuncionarios.getModel();
        String linha[] = new String[5];
        linha[0] = jTFNome.getText();
        linha[1] = jFTFCpf.getText();
        linha[2] = (femininoRadioButton.isSelected()) ? "Feminino " : "Masculino";

        if (manhaCheckBox.isSelected()) {
          linha[3] = "Manha";
          // precisa terminar de fazer a lógica
        }

        DefaultListModel<String> modeloLista = (DefaultListModel<String>) jListPermissoes.getModel();

        linha[4] = modeloLista.toString();

        modelo.addRow(linha);

        // limpando todos os campos
        jTFNome.setText("");
        jFTFCpf.setText("");
        manhaCheckBox.setSelected(false);
        tardeCheckBox.setSelected(false);
        noiteCheckBox.setSelected(false);
        femininoRadioButton.setSelected(false);
        masculinoRadioButton.setSelected(false);
        modeloLista.clear();

        //mudando o foco do teclado para o campo Nome
        jTFNome.requestFocus();
      }
    });
    tableFuncionarios.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        if (tableFuncionarios.getSelectedRow() >= 0){
          excluirLinhaButton.setEnabled(true);
        }


      }
    });
    excluirLinhaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DefaultTableModel modelo = (DefaultTableModel) tableFuncionarios.getModel();
        int totalLinhasSelecionadas  = tableFuncionarios.getSelectedRows().length;

        for (int i = 0; i < totalLinhasSelecionadas; i++) {
          modelo.removeRow(tableFuncionarios.getSelectedRow());
        }

      }
    });
    jFTFCpf.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        super.focusGained(e);
        jFTFCpf.selectAll();
        jFTFCpf.setCaretPosition(0);

      }
    });
    button1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Sobre sobre = new Sobre();
        sobre.pack();
        sobre.setLocationRelativeTo(painelPrincipal);
        sobre.setVisible(true);
      }
    });
  }

  // Método gerado automaticamente pelo IntelliJ: Generate -> Form main
  public static void main(String[] args) {
    JFrame frame = new JFrame("Exemplo com Java Swing e IntelliJ");

    // Fiz uma pequena alteração no código gerado originalmente pelo IntelliJ, pois
    // eu gostaria de definir o focus para o componente jTFNome.
    Principal p = new Principal();
    frame.setContentPane(p.painelPrincipal);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();

    // Requisitando o focus -- linha abaixo foi inserida manualmente
    p.jTFNome.requestFocusInWindow();

    // Centralizando o formulário na tela -- linha abaixo foi inserida manualmente
    frame.setLocationRelativeTo(null);

    frame.setVisible(true);
  }

  private void createUIComponents() {
    // TODO: place custom component creation code here

    // Necessário definir manualmente o TableModel, pois o IntelliJ não apresenta interface para tal
    tableFuncionarios = new JTable();
    tableFuncionarios.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "CPF", "Sexo", "Turna","Permissões"}));
    tableFuncionarios.setFocusable(false);

    jListPermissoes = new JList();
    jListPermissoes.setModel(new DefaultListModel<String>());


    jFTFCpf = new JFormattedTextField();
    try {
      jFTFCpf.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
    } catch (ParseException e) {
      e.printStackTrace();
    }


  }
}
