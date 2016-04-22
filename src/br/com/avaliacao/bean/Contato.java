package br.com.avaliacao.bean;

public class Contato {
	
	private String nome;
	private String telefone;
	private String email;
	private String facebook;
	private String twitter;
	private String observacoes;
	
	public Contato(String nome, String telefone, String email, String facebook,
			String twitter, String observacoes) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.facebook = facebook;
		this.twitter = twitter;
		this.observacoes = observacoes;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", telefone=" + telefone + ", email="
				+ email + ", facebook=" + facebook + ", twitter=" + twitter
				+ ", observacoes=" + observacoes + "]";
	}
	
}
