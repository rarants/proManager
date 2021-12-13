/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rarantes.ProManager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rarantes.ProManager.Models.Pessoa;

/**
 *
 * @author Raíssa
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
