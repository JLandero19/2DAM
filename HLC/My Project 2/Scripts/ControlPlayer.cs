using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ControlPlayer : MonoBehaviour
{
    public int fuerzaSalto;
    public int velocidad;
    private Rigidbody2D fisicas;
    private SpriteRenderer sprite;
    private Animator animator;
    private float entradaX;

    // Start is called before the first frame update
    void Start()
    {
        // Inicializamos el componentes de las físicas
        fisicas = GetComponent<Rigidbody2D>();
        sprite = GetComponentInChildren<SpriteRenderer>();
        sprite.flipX = false;
        animator = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        // Espera que apulsemos el botón espacio y TocandoSuelo() nos permite controlar el salto infinito
        if (Input.GetKeyDown(KeyCode.Space) && TocandoSuelo())
        {
            fisicas.AddForce(Vector2.up * fuerzaSalto, ForceMode2D.Impulse);
        }

    }

    private void FixedUpdate()
    {
        entradaX = Input.GetAxis("Horizontal");
        fisicas.velocity = new Vector2(entradaX * velocidad, fisicas.velocity.y);
        AnimatePlayer24();
        
        if (fisicas.velocity.x > 0)
        {
            sprite.flipX = false;            
        } else if (fisicas.velocity.x < 0)
        {
            sprite.flipX = true;
        }

        
    }

    private bool TocandoSuelo()
    {
        // Esto lo que hace mirar si está tocando el suelo o no
        RaycastHit2D toca = Physics2D.Raycast(transform.position + new Vector3(0, -1.6f, 0), Vector2.down, 0.2f);
        return toca.collider != null;
    }

    internal void FinJuego()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
    }

    private void AnimatePlayer24()
    {
        animator.SetBool("runner", entradaX != 0);
    }

}
