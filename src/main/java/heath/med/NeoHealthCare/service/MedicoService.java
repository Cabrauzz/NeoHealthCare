package heath.med.NeoHealthCare.service;


import heath.med.NeoHealthCare.domain.medico.DadosDetalhamentoMedico;
import heath.med.NeoHealthCare.domain.medico.Medico;
import heath.med.NeoHealthCare.domain.medico.MedicoRepository;
import heath.med.NeoHealthCare.dto.MedicoUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

  @Autowired
  private MedicoRepository medicoRepository;

  public Page<Medico> listarMedicos(Pageable pageable) {
    return medicoRepository.findByAtivoTrue(pageable);
  }

  public Medico cadastrarMedico(Medico medidicoDTO) {
    medicoRepository.save(medidicoDTO);
    return medidicoDTO;
  }

  public Medico atualizaMedico(Long id, MedicoUpdateDTO medicoUpdateDTO) {
    var load = medicoRepository.getReferenceById(id);
    load.atualizarInformacao(medicoUpdateDTO);
    return medicoRepository.save(load);
  }

  public void deletarMedico(Long id) {
    var load = medicoRepository.getReferenceById(id);
    load.excluir();
    medicoRepository.save(load);
  }

  public DadosDetalhamentoMedico detalharMedico(Long id) {
    var load = medicoRepository.getReferenceById(id);
    medicoRepository.save(load);
    return new DadosDetalhamentoMedico(load);
  }
}