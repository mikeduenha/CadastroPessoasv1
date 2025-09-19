package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.model.PessoaJuridicaModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaFisicaRepository;
import Pessoas.example.CadastroPessoas.repository.PessoaJuridicaRepository;
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
import org.springframework.web.server.ResponseStatusException;

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

    @Mock
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Mock
    private PessoaJuridicaRepository pessoaJuridicaRepository;

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
    class AddPessoa{

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
    class FindById{

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
    class FindByCelular{

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
    class Listar{

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
    class DeletePessoa{

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
    class UpdPessoa{

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
    class CreateEndereco {

        @Test
        @DisplayName("Should create a address with sucess")
        void shouldAddAddressWithSucess() {

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
            doReturn(pessoaEndereco).when(pessoaEnderecoRepository).save(captorEndereco.capture());

            //Act
            var output = pessoaService.createEndereco(pessoa.getIdPessoa(), pessoaEndereco);
            assertNotNull(output);
            verify(pessoaRepository, times(1)).findById(pessoa.getIdPessoa());
        }

        @Test
        @DisplayName("Should return null when pessoa not exists")
        void shouldReturnNullWhenPessoaNotFound() {
            // Arrange
            var enderecoInput = new PessoaEnderecoModel();
            doReturn(Optional.empty()).when(pessoaRepository).findById(2);

            // Act
            var result = pessoaService.createEndereco(2, enderecoInput);

            // Assert
            assertNull(result);
        }
    }

        @Nested
        class UpdEndereco {

            @Test
            @DisplayName("Should update endereço when pessoa exists")
            void shouldUpdateEnderecoWhenPessoaExists() {
                // Arrange
                var pessoa = new PessoaModel();
                pessoa.setIdPessoa(1);

                var enderecoExistente = new PessoaEnderecoModel();
                enderecoExistente.setPessoa(pessoa);

                var enderecoInput = new PessoaEnderecoModel();
                enderecoInput.setCep("novoCep");

                when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));
                when(pessoaEnderecoRepository.findByPessoa(pessoa)).thenReturn(Optional.of(enderecoExistente));
                when(pessoaEnderecoRepository.save(any(PessoaEnderecoModel.class))).thenReturn(enderecoExistente);

                // Act
                var result = pessoaService.updEndereco(1, enderecoInput);

                // Assert
                assertTrue(result.isPresent());
                assertEquals("novoCep", result.get().getCep());
                verify(pessoaEnderecoRepository, times(1)).save(enderecoExistente);
            }

            @Test
            @DisplayName("Should return Optional.empty when pessoa not exist")
            void shouldReturnEmptyWhenPessoaNotExists() {
                // Arrange
                when(pessoaRepository.findById(2)).thenReturn(Optional.empty());

                // Act
                var result = pessoaService.updEndereco(2, new PessoaEnderecoModel());

                // Assert
                assertTrue(result.isEmpty());
                verify(pessoaEnderecoRepository, never()).save(any());
            }
    }

    @Nested
    class FindEndereco {

        @Test
        @DisplayName("Should return endereço when pessoa exists")
        void shouldReturnEnderecoWhenPessoaExists() {
            // Arrange
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            var endereco = new PessoaEnderecoModel();
            endereco.setPessoa(pessoa);
            endereco.setCep("12345-678");

            when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));
            when(pessoaEnderecoRepository.findByPessoa(pessoa)).thenReturn(Optional.of(endereco));

            // Act
            var result = pessoaService.findEndereco(1);

            // Assert
            assertTrue(result.isPresent());
            assertEquals("12345-678", result.get().getCep());
            verify(pessoaRepository, times(1)).findById(1);
            verify(pessoaEnderecoRepository, times(1)).findByPessoa(pessoa);
        }

        @Test
        @DisplayName("Should exception when pessoa not exists")
        void shouldThrowExceptionWhenPessoaNotExists() {
            // Arrange
            when(pessoaRepository.findById(2)).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(ResponseStatusException.class, () -> pessoaService.findEndereco(2));
            verify(pessoaRepository, times(1)).findById(2);
            verify(pessoaEnderecoRepository, never()).findByPessoa(any());
        }
    }

    @Nested
    class CreatePF {

        @Test
        @DisplayName("Should create PessoaFisica when pessoa exists")
        void shouldCreatePFWhenPessoaExists() {
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            var pfInput = new PessoaFisicaModel();
            pfInput.setCpf("123");
            pfInput.setRg("456");
            pfInput.setDatanasc("2000-01-01");

            var pfSaved = new PessoaFisicaModel();
            pfSaved.setPessoa(pessoa);
            pfSaved.setCpf("123");
            pfSaved.setRg("456");
            pfSaved.setDatanasc("2000-01-01");

            when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));
            when(pessoaFisicaRepository.save(any(PessoaFisicaModel.class))).thenReturn(pfSaved);

            var result = pessoaService.createPF(1, pfInput);

            assertNotNull(result);
            assertEquals("123", result.getCpf());
            verify(pessoaFisicaRepository, times(1)).save(any(PessoaFisicaModel.class));
        }

        @Test
        @DisplayName("Should return null when pessoa not exists")
        void shouldReturnNullWhenPessoaNotExists() {
            var pfInput = new PessoaFisicaModel();
            when(pessoaRepository.findById(2)).thenReturn(Optional.empty());

            var result = pessoaService.createPF(2, pfInput);

            assertNull(result);
            verify(pessoaFisicaRepository, never()).save(any());
        }
    }

    @Nested
    class FindPF {

        @Test
        @DisplayName("Should return PessoaFisica when pessoa exists")
        void shouldReturnPFWhenPessoaExists() {
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            var pf = new PessoaFisicaModel();
            pf.setPessoa(pessoa);
            pf.setCpf("123");

            when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));
            when(pessoaFisicaRepository.findByPessoa(pessoa)).thenReturn(Optional.of(pf));

            var result = pessoaService.findPF(1);

            assertTrue(result.isPresent());
            assertEquals("123", result.get().getCpf());
            verify(pessoaFisicaRepository, times(1)).findByPessoa(pessoa);
        }

        @Test
        @DisplayName("Should exception when pessoa not exists")
        void shouldThrowExceptionWhenPessoaNotExists() {
            when(pessoaRepository.findById(2)).thenReturn(Optional.empty());

            assertThrows(ResponseStatusException.class, () -> pessoaService.findPF(2));
            verify(pessoaFisicaRepository, never()).findByPessoa(any());
        }
    }

    @Nested
    class UpdPF {

        @Test
        @DisplayName("Should update PessoaFisica when pessoa exists")
        void shouldUpdatePFWhenPessoaExists() {
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            var pfExistente = new PessoaFisicaModel();
            pfExistente.setPessoa(pessoa);
            pfExistente.setCpf("oldCpf");

            var pfInput = new PessoaFisicaModel();
            pfInput.setCpf("newCpf");
            pfInput.setRg("newRg");
            pfInput.setDatanasc("2020-01-01");

            when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));
            when(pessoaFisicaRepository.findByPessoa(pessoa)).thenReturn(Optional.of(pfExistente));
            when(pessoaFisicaRepository.save(any(PessoaFisicaModel.class))).thenReturn(pfExistente);

            var result = pessoaService.updPF(1, pfInput);

            assertTrue(result.isPresent());
            assertEquals("newCpf", result.get().getCpf());
            verify(pessoaFisicaRepository, times(1)).save(pfExistente);
        }

        @Test
        @DisplayName("Should return Optional.empty when pessoa not exists")
        void shouldReturnEmptyWhenPessoaNotExists() {
            var pfInput = new PessoaFisicaModel();
            when(pessoaRepository.findById(2)).thenReturn(Optional.empty());

            var result = pessoaService.updPF(2, pfInput);

            assertTrue(result.isEmpty());
            verify(pessoaFisicaRepository, never()).save(any());
        }
    }
    @Nested
    class CreatePJ {

        @Test
        @DisplayName("Should create PessoaJuridica when pessoa exists")
        void shouldCreatePJWhenPessoaExists() {
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            var pjInput = new PessoaJuridicaModel();
            pjInput.setCnpj("123456789");
            pjInput.setRazaosocial("Empresa X");

            var pjSaved = new PessoaJuridicaModel();
            pjSaved.setPessoa(pessoa);
            pjSaved.setCnpj("123456789");
            pjSaved.setRazaosocial("Empresa X");

            when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));
            when(pessoaJuridicaRepository.save(any(PessoaJuridicaModel.class))).thenReturn(pjSaved);

            var result = pessoaService.createPJ(1, pjInput);

            assertNotNull(result);
            assertEquals("123456789", result.getCnpj());
            verify(pessoaJuridicaRepository, times(1)).save(any(PessoaJuridicaModel.class));
        }

        @Test
        @DisplayName("Should return null when pessoa not exists")
        void shouldReturnNullWhenPessoaNotExists() {
            var pjInput = new PessoaJuridicaModel();
            when(pessoaRepository.findById(2)).thenReturn(Optional.empty());

            var result = pessoaService.createPJ(2, pjInput);

            assertNull(result);
            verify(pessoaJuridicaRepository, never()).save(any());
        }
    }

    @Nested
    class FindPJ {

        @Test
        @DisplayName("Should return PessoaJuridica when pessoa exists")
        void shouldReturnPJWhenPessoaExists() {
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            var pj = new PessoaJuridicaModel();
            pj.setPessoa(pessoa);
            pj.setCnpj("123456789");

            when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));
            when(pessoaJuridicaRepository.findByPessoa(pessoa)).thenReturn(Optional.of(pj));

            var result = pessoaService.findPJ(1);

            assertTrue(result.isPresent());
            assertEquals("123456789", result.get().getCnpj());
            verify(pessoaJuridicaRepository, times(1)).findByPessoa(pessoa);
        }

        @Test
        @DisplayName("Should exception when pessoa not exists")
        void shouldThrowExceptionWhenPessoaNotExists() {
            when(pessoaRepository.findById(2)).thenReturn(Optional.empty());

            assertThrows(ResponseStatusException.class, () -> pessoaService.findPJ(2));
            verify(pessoaJuridicaRepository, never()).findByPessoa(any());
        }
    }

    @Nested
    class UpdPJ {

        @Test
        @DisplayName("Should update PessoaJuridica when pessoa exists")
        void shouldUpdatePJWhenPessoaExists() {
            var pessoa = new PessoaModel();
            pessoa.setIdPessoa(1);

            var pjExistente = new PessoaJuridicaModel();
            pjExistente.setPessoa(pessoa);
            pjExistente.setCnpj("oldCnpj");

            var pjInput = new PessoaJuridicaModel();
            pjInput.setCnpj("newCnpj");
            pjInput.setRazaosocial("Nova Empresa");

            when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));
            when(pessoaJuridicaRepository.findByPessoa(pessoa)).thenReturn(Optional.of(pjExistente));
            when(pessoaJuridicaRepository.save(any(PessoaJuridicaModel.class))).thenReturn(pjExistente);

            var result = pessoaService.updPJ(1, pjInput);

            assertTrue(result.isPresent());
            assertEquals("newCnpj", result.get().getCnpj());
            verify(pessoaJuridicaRepository, times(1)).save(pjExistente);
        }

        @Test
        @DisplayName("Should return Optional.empty when pessoa not exists")
        void shouldReturnEmptyWhenPessoaNotExists() {
            var pjInput = new PessoaJuridicaModel();
            when(pessoaRepository.findById(2)).thenReturn(Optional.empty());

            var result = pessoaService.updPJ(2, pjInput);

            assertTrue(result.isEmpty());
            verify(pessoaJuridicaRepository, never()).save(any());
        }
    }
}