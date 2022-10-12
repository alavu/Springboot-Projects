package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alavu.fileupload_in_springboot_csv_to_db.entity.AccountDetails;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails,Integer> {
     
}
