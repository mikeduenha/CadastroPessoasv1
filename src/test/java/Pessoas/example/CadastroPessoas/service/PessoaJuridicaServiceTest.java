package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaJuridicaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaJuridicaRepository;
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
class PessoaJuridicaServiceTest {

    @Mock
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @InjectMocks
    private PessoaJuridicaService pessoaJuridicaService;

    @Captor
    private ArgumentCaptor<Integer> captorPessoaId;

    @Nested
    class findById{

        @Test
        @DisplayName("Should get user by id with sucess with optional is present")
        void shouldfindByIdPJWithSucess(){

            //arrange
            var pessoa = new PessoaJuridicaModel();
            pessoa.setIdPessoaPJ(1);
            pessoa.setCnpj("cpf");
            pessoa.setRazaosocial("razaosocial");

            doReturn(Optional.of(pessoa)).when(pessoaJuridicaRepository).findById(captorPessoaId.capture());

            //act
            var output = pessoaJuridicaService.findById(pessoa.getIdPessoaPJ());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(pessoa.getIdPessoaPJ(), captorPessoaId.getValue());
        }

        @Test
        @DisplayName("Should get user by id with sucess with optional is empty")
        void shouldfindByIdPFIsEmpty(){

            // Arrange
            var pessoa = new PessoaJuridicaModel();
            pessoa.setIdPessoaPJ(1);

            doReturn(Optional.empty()).when(pessoaJuridicaRepository).findById(captorPessoaId.capture());

            //act
            var output = pessoaJuridicaRepository.findById(pessoa.getIdPessoaPJ());

            //Assert
            assertTrue(output.isEmpty());
        }
    }
    @Nested
    class listar{

        @Test
        @DisplayName("Should return all address with sucess")
        void shouldReturnAllPFWithSucess(){

            //arrange
            var pessoa = new PessoaJuridicaModel();
            pessoa.setIdPessoaPJ(1);
            pessoa.setCnpj("cpf");
            pessoa.setRazaosocial("razaosocial");

            doReturn(List.of(pessoa)).when(pessoaJuridicaRepository).findAll();

            //Act
            var output = pessoaJuridicaService.listar();

            //Assert
            assertNotNull(output);
        }
    }
}