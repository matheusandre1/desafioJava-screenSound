package com.example.screenSound.principal;

import com.example.screenSound.models.Artista;
import com.example.screenSound.models.Musica;
import com.example.screenSound.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);

    private final ArtistaRepository _artistaRepository;

    public Principal(ArtistaRepository artistaRepository)
    {
        this._artistaRepository = artistaRepository;
    }


    public void Menu() {
        var opcao = -1;
        while (opcao != 9) {

            var menu = """
                    *** ScreenSound ***
                    
                    1 - Cadastrar Artista
                    2 - Cadastrar Musica
                    3 - Listar Músicas
                    4 - Buscar Musica por Artista
                    
                    
                    9 - Sair
                    """;

            System.out.printf(menu);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicaPorArtista();
                    break;
            }
        }



    }




    private void cadastrarArtista()
    {
        String opcao = "";
        while (!opcao.toLowerCase().equalsIgnoreCase("n")) {
            System.out.println("Informe o nome desse artista:");
            var nome = scanner.nextLine();
            System.out.println("Informe o tipo desse artista: (solo, dupla,banda)");
            var tipo = scanner.nextLine();

            Artista artista = new Artista(nome, tipo);
            _artistaRepository.save(artista);
            System.out.println("Cadastrar outro Artista? (S/N)");
            opcao = scanner.nextLine();
        }

    }


    private void cadastrarMusica() {
        System.out.println("Cadastrar Música de que artista? ");
        var nome = scanner.nextLine();

        Optional<Artista> artista = _artistaRepository.findByNomeContainingIgnoreCase(nome);

        if(artista.isPresent())
        {
            System.out.println("Qual o nome da Música: ");
            var nomeMusica = scanner.nextLine();

            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            _artistaRepository.save(artista.get());
        }
        else
        {
            System.out.println("Não encontrado");
        }

    }

    private void listarMusicas() {

        List<Artista> artistas = _artistaRepository.findAll();

        artistas.forEach(System.out::println);
    }

    private void buscarMusicaPorArtista()
    {
        System.out.println("Busca Música por Artista: ");
        var nome = scanner.nextLine();

        List<Musica> musicas = _artistaRepository.buscaMusicasPorArtista(nome);

        musicas.forEach(System.out::println);
    }

}
