package br.com.unisul.grafos.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.unisul.grafos.impl.Grafo;
import br.com.unisul.grafos.impl.GrafoListaAdj;


public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel _painelInfo;
	private JPanel _painelDesenho;
	private JPanel _painelBotoes;
	private JPanel _painelInfoGrafo;
	private JPanel _painelSaidaDoGrafo;
	
	private JRadioButton _radioDirecionado;
	private JRadioButton _radioNaoDirecionado;
	private JRadioButton _radioVertice;
	private JRadioButton _radioAresta;
	
	private JButton _botaoListaAdj;
	private JButton _botaoMatrizAdj;
	private JButton _botaoMatrizIncidencia;
	private JButton _botaoListaArestas;
	private JButton _botaoNovoGrafo;
	
	private JTextArea _saidaDoGrafo;
	
	private Grafo _grafo;
	
	public Tela(String titulo) throws HeadlessException {
		
		setLayout(new BorderLayout());
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(1000,670));
		setPreferredSize(getSize());
		setLocationRelativeTo(null);
		setResizable(true);
		
		_grafo = new Grafo();
		
		getContentPane().add(getPainelInfo(), BorderLayout.NORTH);
		getContentPane().add(getPainelDesenho(), BorderLayout.CENTER);
		getContentPane().add(getPainelInfoGrafo(), BorderLayout.WEST);
		getContentPane().add(getPainelSaidaDoGrafo(), BorderLayout.EAST);
		getContentPane().add(getPainelBotoes(), BorderLayout.SOUTH);
	}
	
	private JPanel getPainelSaidaDoGrafo() {
		if (_painelSaidaDoGrafo == null) {
			_painelSaidaDoGrafo = new JPanel();
			
			_painelSaidaDoGrafo.setBorder(BorderFactory.createTitledBorder("Resultado do grafo"));
			_painelSaidaDoGrafo.setLayout(new FlowLayout());
			_painelSaidaDoGrafo.setSize(new Dimension(200, 200));
			
			_saidaDoGrafo = new JTextArea("### GRAFOS ###\n", 100, 30);
			JScrollPane scrollPanel = new JScrollPane(_saidaDoGrafo);
			
			_painelSaidaDoGrafo.add(scrollPanel);
		}
		
		return _painelSaidaDoGrafo;
	}
	
	private JPanel getPainelInfoGrafo() {
		if (_painelInfoGrafo == null) {
			_painelInfoGrafo = new JPanel();
			
			_painelInfoGrafo.setBorder(BorderFactory.createTitledBorder("Inserir no Grafo"));
			_painelInfoGrafo.setLayout(new FlowLayout());
			_painelInfoGrafo.setSize(new Dimension(50, 200));
			
			_radioVertice = new JRadioButton("Vertice");
			_radioAresta = new JRadioButton("Aresta");
			_radioVertice.setSelected(true);
			
			final ButtonGroup grupoRadio = new ButtonGroup();
			grupoRadio.add(_radioVertice);
			grupoRadio.add(_radioAresta);
			
			_painelInfoGrafo.add(_radioVertice);
			_painelInfoGrafo.add(_radioAresta);
			
		}
		
		return _painelInfoGrafo;
	}
	
	private JPanel getPainelInfo() {
		if (_painelInfo == null) {
			_painelInfo = new JPanel();
			
			_painelInfo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
			_painelInfo.setLayout(new FlowLayout());
			_painelInfo.setSize(new Dimension(100, 50));
			
			_botaoNovoGrafo = new JButton("Novo Grafo");
			_painelInfo.add(_botaoNovoGrafo);
			
			_radioDirecionado = new JRadioButton("Direcionado");
			_radioNaoDirecionado = new JRadioButton("Não direcionado");
			_radioDirecionado.setSelected(true);
			
			final ButtonGroup grupoRadio = new ButtonGroup();
			grupoRadio.add(_radioDirecionado);
			grupoRadio.add(_radioNaoDirecionado);
			
			_painelInfo.add(_radioDirecionado);
			_painelInfo.add(_radioNaoDirecionado);
			
		}
		return _painelInfo;
	}
	
	private JPanel getPainelDesenho() {
		if (_painelDesenho == null) {
			_painelDesenho = new PainelDesenho(this, _grafo);
		}
		return _painelDesenho;
	}
	
	private JPanel getPainelBotoes() {
		if (_painelBotoes == null) {
			_painelBotoes = new JPanel();
			
			_painelBotoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
			_painelBotoes.setLayout(new FlowLayout());
			_painelBotoes.setSize(new Dimension(1000, 50));
			
			_botaoListaAdj = new JButton("Gerar Lista de Adjacencia");
			_botaoListaAdj.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					geraGrafoAPartirDo(new GrafoListaAdj());
				}
			});
			
			
			_botaoMatrizAdj = new JButton("Gerar Matriz de Adjacencia");
			_botaoMatrizIncidencia = new JButton("Gerar Matriz de Incidencia");
			_botaoListaArestas = new JButton("Gerar Lista de Arestas");
			
			_painelBotoes.add(_botaoListaAdj);
			_painelBotoes.add(_botaoMatrizAdj);
			_painelBotoes.add(_botaoMatrizIncidencia);
			_painelBotoes.add(_botaoListaArestas);
		
		}
		return _painelBotoes;
	}
	
	public void geraGrafoAPartirDo(final Grafo grafo) {
		if (grafo instanceof GrafoListaAdj) {
			GrafoListaAdj grafoListaAdj = (GrafoListaAdj) _grafo;
			_saidaDoGrafo.setText(grafoListaAdj.toString());
		}
		
	}
	
	public JRadioButton getRadioDirecionado() {
		return _radioDirecionado;
	}
	
	public JRadioButton getRadioNaoDirecionado() {
		return _radioNaoDirecionado;
	}
	
	public JRadioButton getRadioVertice() {
		return _radioVertice;
	}

	public JRadioButton getRadioAresta() {
		return _radioAresta;
	}

	public static void main(String[] args) {
		final Tela tela = new Tela("Trabalho representação de grafos");
		tela.setVisible(true);
	}
	
}
