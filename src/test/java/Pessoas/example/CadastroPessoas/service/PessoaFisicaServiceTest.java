package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaFisicaRepository;
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
class PessoaFisicaServiceTest {

    @Mock
    private PessoaFisicaRepository pessoaFisicaRepository;

    @InjectMocks
    private PessoaFisicaService pessoaFisicaService;

    @Captor
    private ArgumentCaptor<Integer> captorPessoaId;

    @Nested
    class FindById{

        @Test
        @DisplayName("Should get user by id with sucess with optional is present")
        void shouldfindByIdPFWithSucess(){

            //arrange
            var pessoa = new PessoaFisicaModel();
            pessoa.setIdPessoaPF(1);
            pessoa.setCpf("cpf");
            pessoa.setRg("rg");
            pessoa.setDatanasc("datanascimento");


            doReturn(Optional.of(pessoa)).when(pessoaFisicaRepository).findById(captorPessoaId.capture());

            //act
            var output = pessoaFisicaService.findById(pessoa.getIdPessoaPF());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(pessoa.getIdPessoaPF(), captorPessoaId.getValue());
        }

        @Test
        @DisplayName("Should get user by id with sucess with optional is empty")
        void shouldfindByIdPFIsEmpty(){

            // Arrange
            var pessoa = new PessoaFisicaModel();
            pessoa.setIdPessoaPF(1);

            doReturn(Optional.empty()).when(pessoaFisicaRepository).findById(captorPessoaId.capture());

            //act
            var output = pessoaFisicaRepository.findById(pessoa.getIdPessoaPF());

            //Assert
            assertTrue(output.isEmpty());
        }
    }
    @Nested
    class Listar{

        @Test
        @DisplayName("Should return all address with sucess")
        void shouldReturnAllPFWithSucess(){

            //arrange
            var pessoa = new PessoaFisicaModel();
            pessoa.setIdPessoaPF(1);
            pessoa.setCpf("cpf");
            pessoa.setRg("rg");
            pessoa.setDatanasc("datanascimento");

            doReturn(List.of(pessoa)).when(pessoaFisicaRepository).findAll();

            //Act
            var output = pessoaFisicaService.listar();

            //Assert
            assertNotNull(output);
        }
    }
}