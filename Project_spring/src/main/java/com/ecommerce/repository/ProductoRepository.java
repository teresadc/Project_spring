import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Puedes agregar métodos personalizados, por ejemplo:
    User findByProducto(String nombre);
}