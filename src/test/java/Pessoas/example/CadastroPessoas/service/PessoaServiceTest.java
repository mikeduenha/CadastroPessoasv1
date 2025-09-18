package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaRepository;
import Pessoas.example.CadastroPessoas.repository.PessoaEnderecoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {
    //arrange
    //act
    //assert
    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaEnderecoRepository pessoaEnderecoRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Captor
    private ArgumentCaptor<PessoaModel> captorPessoa = ArgumentCaptor.forClass(PessoaModel.class);

    @Captor
    private ArgumentCaptor<PessoaEnderecoModel> captorEndereco = ArgumentCaptor.forClass(PessoaEnderecoModel.class);

    @Captor
    private ArgumentCaptor<Integer> captorPessoaId;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Nested
    class addPessoa{

        @Test
        @DisplayName("Should create a user with sucess")
        void shouldAddPessoaWithSucess(){
            // Arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);
            pessoa.setNome("nome");
            pessoa.setEmail("email");
            pessoa.setTelefone("telefone");
            pessoa.setCelular("celular");

            doReturn(pessoa).when(pessoaRepository).save(captorPessoa.capture());

            //Act
            pessoaService.addPessoa(pessoa);
            assertEquals(pessoa.getIdPessoa(), captorPessoa.getValue().getIdPessoa());
            assertEquals(pessoa.getNome(), captorPessoa.getValue().getNome());
            assertEquals(pessoa.getEmail(), captorPessoa.getValue().getEmail());
            assertEquals(pessoa.getTelefone(), captorPessoa.getValue().getTelefone());
            assertEquals(pessoa.getCelular(), captorPessoa.getValue().getCelular());
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenAddPessoaErrorOccurs(){
            // Arrange
            doThrow(new RuntimeException()).when(pessoaRepository).save(any());
            var input = new PessoaModel();

            input.setIdPessoa(1);
            input.setNome("nome");
            input.setEmail("email");
            input.setTelefone("telefone");
            input.setCelular("celular");

            //Act
            assertThrows(RuntimeException.class, () -> pessoaService.addPessoa(input));
        }
    }

    @Nested
    class findById{

        @Test
        @DisplayName("Should get user by id with sucess with optional is present")
        void shouldfindByIdPessoaWithSucess(){

            //arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);
            pessoa.setNome("nome");
            pessoa.setEmail("email");
            pessoa.setTelefone("telefone");
            pessoa.setCelular("celular");

            doReturn(Optional.of(pessoa)).when(pessoaRepository).findById(captorPessoaId.capture());

            //act
            var output = pessoaService.findById(pessoa.getIdPessoa());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(pessoa.getIdPessoa(), captorPessoaId.getValue());

        }

        @Test
        @DisplayName("Should get user by id with sucess with optional is empty")
        void shouldfindByIdPessoaIsEmpty(){

            // Arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            doReturn(Optional.empty()).when(pessoaRepository).findById(captorPessoaId.capture());

            //act
            var output = pessoaService.findById(pessoa.getIdPessoa());

            //Assert
            assertTrue(output.isEmpty());
        }
    }

    @Nested
    class findByCelular{

        @Test
        @DisplayName("Should get user by celular with sucess with optional is present")
        void shouldfindByCelularWithSucess(){

            //arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);
            pessoa.setNome("nome");
            pessoa.setEmail("email");
            pessoa.setTelefone("telefone");
            pessoa.setCelular("celular");

            doReturn(Optional.of(pessoa)).when(pessoaRepository).findByCelular(captorString.capture());

            //act
            var output = pessoaService.findByCelular(pessoa.getCelular());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(pessoa.getCelular(), captorString.getValue());
        }

        @Test
        @DisplayName("Should get user by id with sucess with optional is empty")
        void shouldfindByCelularIsEmpty() {

            // Arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            doReturn(Optional.empty()).when(pessoaRepository).findByCelular(captorString.capture());

            //act
            var output = pessoaService.findByCelular(pessoa.getCelular());

            //Assert
            assertTrue(output.isEmpty());
        }
    }

    @Nested
    class listar{

        @Test
        @DisplayName("Should return all users with sucess")
        void shouldReturnAllUsersWithSucess(){

            //arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);
            pessoa.setNome("nome");
            pessoa.setEmail("email");
            pessoa.setTelefone("telefone");
            pessoa.setCelular("celular");

            doReturn(List.of(pessoa)).when(pessoaRepository).findAll();

            //Act
            var output = pessoaService.listar();

            //Assert
            assertNotNull(output);
        }
    }

    @Nested
    class deletePessoa{

        @Test
        @DisplayName("Should delete user with sucess")
        void shouldDeleteUserWithSucess(){

            //Arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            doReturn(Optional.of(pessoa)).when(pessoaRepository).findById(captorPessoaId.capture());
            doNothing().when(pessoaRepository).deleteById(captorPessoaId.capture());

            //Act
            pessoaService.deletePessoa(pessoa.getIdPessoa());

            //Assert
            var idList = captorPessoaId.getAllValues();
            assertEquals(pessoa.getIdPessoa(), idList.get(0));
            assertEquals(pessoa.getIdPessoa(), idList.get(1));

            verify(pessoaRepository, times(1)).findById(idList.get(0));
            verify(pessoaRepository, times(1)).deleteById(idList.get(1));
        }

        @Test
        @DisplayName("Should not delete user with sucess")
        void shouldNotDeleteUserWithSucess(){

            //Arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            doReturn(Optional.empty()).when(pessoaRepository).findById(captorPessoaId.capture());

            //Act
            pessoaService.deletePessoa(pessoa.getIdPessoa());

            //Assert
            verify(pessoaRepository, times(1)).findById(pessoa.getIdPessoa());

        }
    }

    @Nested
    class updPessoa{

        @Test
        @DisplayName("Should update user by id with sucess when user exists")
        void shouldUpdatePessoaWhenUserExists(){

            //arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);
            pessoa.setNome("nome");
            pessoa.setEmail("email");
            pessoa.setTelefone("telefone");
            pessoa.setCelular("celular");

            doReturn(Optional.of(pessoa)).when(pessoaRepository).findById(captorPessoaId.capture());
            doReturn(pessoa).when(pessoaRepository).save(captorPessoa.capture());

            //Act
            var output = pessoaService.updPessoa(pessoa.getIdPessoa(), pessoa);

            //Assert
            assertTrue(output.isPresent());
            assertEquals(pessoa.getIdPessoa(), captorPessoaId.getValue());
            verify(pessoaRepository, times(1)).save(captorPessoa.capture());
        }

        @Test
        @DisplayName("Should not update user by id with sucess when user not exists")
        void shouldNotUpdatePessoaWhenUserNotExists(){

            //arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            doReturn(Optional.empty()).when(pessoaRepository).findById(captorPessoaId.capture());

            //Act
            var output = pessoaService.updPessoa(pessoa.getIdPessoa(), pessoa);

            //Assert
            assertEquals(pessoa.getIdPessoa(), captorPessoaId.getValue());
            verify(pessoaRepository, times(1)).findById(pessoa.getIdPessoa());
        }
    }


    @Nested
    class createEndereco{

        @Test
        @DisplayName("Should create a address with sucess")
        void shouldAddAddressWithSucess(){

            // Arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);
            pessoa.setNome("nome");
            pessoa.setEmail("email");
            pessoa.setTelefone("telefone");
            pessoa.setCelular("celular");

            var pessoaEndereco = new PessoaEnderecoModel();

            pessoaEndereco.setPessoa(pessoa);
            pessoaEndereco.setCep("cep");
            pessoaEndereco.setLogradouro("logradouro");
            pessoaEndereco.setComplemento("complemento");
            pessoaEndereco.setUnidade("unidade");
            pessoaEndereco.setBairro("bairro");
            pessoaEndereco.setLocalidade("localidade");
            pessoaEndereco.setUf("uf");
            pessoaEndereco.setEstado("estado");
            pessoaEndereco.setRegiao("regiao");
            pessoaEndereco.setIbge("ibge");
            pessoaEndereco.setGia("gia");
            pessoaEndereco.setDdd("ddd");
            pessoaEndereco.setSiafi("siafi");

            doReturn(Optional.of(pessoa)).when(pessoaRepository).findById(captorPessoaId.capture());
            doReturn(Optional.of(pessoaEndereco)).when(pessoaEnderecoRepository).save(captorEndereco.capture());

            //Act
            pessoaService.createEndereco(pessoa.getIdPessoa(), pessoaEndereco);
            verify(pessoaRepository, times(1)).findById(pessoa.getIdPessoa());

        }
    }

}