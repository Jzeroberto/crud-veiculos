package com.lojaveiculos.crud_veiculos.controller;

import com.lojaveiculos.crud_veiculos.model.Veiculo;
import com.lojaveiculos.crud_veiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;


    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    //Listar veículos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("veiculos", veiculoRepository.findAll());
        return "veiculos/lista";
    }

    //Form para novo veículo
    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        return "veiculos/form";
    }

    //Salvar veículo
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Veiculo veiculo,
                         @RequestParam("file") MultipartFile file) throws IOException {


        if (file != null && !file.isEmpty()) {

            String nomeArquivo = StringUtils.cleanPath(file.getOriginalFilename());

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(nomeArquivo);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


            veiculo.setFoto(nomeArquivo);
        }

        veiculoRepository.save(veiculo);
        return "redirect:/veiculos";
    }

    //Editar veiculo
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Veículo inválido:" + id));
        model.addAttribute("veiculo", veiculo);
        return "veiculos/form";
    }

    //Deletar veículo
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
        return "redirect:/veiculos";
    }

    @GetMapping("/veiculos")
    public String listarVeiculos(@RequestParam(required = false) String filtro, Model model) {
        List<Veiculo> veiculos;

        if (filtro != null && !filtro.isBlank()) {
            veiculos = veiculoRepository.buscarPorFiltro(filtro);
        } else {
            veiculos = veiculoRepository.findAll();
        }

        model.addAttribute("veiculos", veiculos);
        return "veiculos";
    }


}
