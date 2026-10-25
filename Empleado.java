/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author lopez
 */
public class Empleado {
    private Integer id;
    private String nombre;
    private String puesto;
    private String departamento;
    private Double salario; 

    public Empleado(Integer id, String nombre, String puesto, String departamento, Double salario) {
        if (id == null || id <= 0) throw new IllegalArgumentException("El ID debe ser entero positivo.");
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre no debe estar vacio");
        if (puesto == null || puesto.trim().isEmpty()) throw new IllegalArgumentException("El puesto no debe estar vacio");
        if (departamento == null || departamento.trim().isEmpty()) throw new IllegalArgumentException("El departamento no debe estar vacio");
        if (salario == null || salario < 0 || salario.isInfinite() || salario.isNaN()) throw new IllegalArgumentException("El salario debe ser valido y no negativo");
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.departamento = departamento;
        this.salario = salario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", puesto=" + puesto + ", departamento=" + departamento + ", salario=" + salario + '}';
    }
    
    
    
}
