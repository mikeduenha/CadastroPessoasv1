package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
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
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PessoaEnderecoServiceTest {
    @Mock
    private PessoaEnderecoRepository pessoaEnderecoRepository;

    @InjectMocks
    private PessoaEnderecoService pessoaEnderecoService;

    @Captor
    private ArgumentCaptor<Integer> captorEnderecoId;

    @Nested
    class findById{

        @Test
        @DisplayName("Should get Address with sucess with optional is present")
        void shouldfindByIdAddressWithSucess(){

            //arrange
            var pessoaEndereco = new PessoaEnderecoModel();
            pessoaEndereco.setIdEndereco(1);
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

            doReturn(Optional.of(pessoaEndereco)).when(pessoaEnderecoRepository).findById(captorEnderecoId.capture());

            //act
            var output = pessoaEnderecoService.findById(pessoaEndereco.getIdEndereco());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(pessoaEndereco.getIdEndereco(), captorEnderecoId.getValue());
        }

        @Test
        @DisplayName("Should get user address with sucess with optional is empty")
        void shouldfindByIdAddressIsEmpty(){

            // Arrange
            var endereco = new PessoaEnderecoModel();
            endereco.setIdEndereco(1);

            doReturn(Optional.empty()).when(pessoaEnderecoRepository).findById(captorEnderecoId.capture());

            //act
            var output = pessoaEnderecoService.findById(endereco.getIdEndereco());

            //Assert
            assertTrue(output.isEmpty());
        }
    }
    @Nested
    class listar{

        @Test
        @DisplayName("Should return all address with sucess")
        void shouldReturnAllAddressWithSucess(){

            //arrange
            var pessoaEndereco = new PessoaEnderecoModel();
            pessoaEndereco.setIdEndereco(1);
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

            doReturn(List.of(pessoaEndereco)).when(pessoaEnderecoRepository).findAll();

            //Act
            var output = pessoaEnderecoService.listar();

            //Assert
            assertNotNull(output);
        }
    }
}