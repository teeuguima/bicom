package facade;

import controladores.ControladorDeDados;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import model.Perfil;
import util.ConvertKey;

public class ServidorFacade {

    private final ControladorDeDados dados;

    private static ServidorFacade facade;
    private ConvertKey convert;

    /**
     * Méodo construtor se inicializa instanciando cada um os controladores.
     * Essa classe é o que vai acessar todos os controladores e vai ser a classe
     * disponível para quem quiser acessar o programa. As operações só serão
     * feitas a partir desta classe, e nenhum controlador vai ser acessado senão
     * por ela.
     */
    public ServidorFacade() throws IOException, FileNotFoundException, ClassNotFoundException {
        dados = new ControladorDeDados();
 
        convert = new ConvertKey();
    }

    public static synchronized ServidorFacade getInstance() throws IOException, FileNotFoundException, ClassNotFoundException {
        return (facade == null) ? facade = new ServidorFacade() : facade;
    }

    public String[] cadastrarPerfil(String nome, String sobrenome, String cpf, String rg, String email, String telefone, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return null;
    }

    public Perfil realizarLogin(String cpf, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return this.dados.realizarLogin(cpf, senha);
    }

    /**
     * PERSISTÊNCIA DE DADOS
     *
     * @throws IOException
     */
    public void criandoArquivos() throws IOException, FileNotFoundException, ClassNotFoundException {
        dados.criandoArquivos();
    }

    public void lerDados() throws IOException, FileNotFoundException, ClassNotFoundException {
        dados.lendoDados();
    }

    public void armazenarDados() throws IOException {
        dados.salvandoDados();
    }

}
