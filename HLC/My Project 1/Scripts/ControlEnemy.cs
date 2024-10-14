using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ControlEnemy : MonoBehaviour
{
    public float velocidad;
    private Vector3 posicionInicio;
    public Vector3 posicionFin;
    private bool moviendoAFin;
    private SpriteRenderer sprite;

    // Start is called before the first frame update
    void Start()
    {
        posicionInicio = transform.position;
        moviendoAFin = true;
        sprite = GetComponentInChildren<SpriteRenderer>();
        sprite.flipX = true;
    }

    // Update is called once per frame
    void Update()
    {
        MoverEnemigo();
    }

    private void MoverEnemigo()
    {
        if (moviendoAFin)
        {
            transform.position = Vector3.MoveTowards(transform.position, posicionFin, velocidad * Time.deltaTime);
            if (transform.position == posicionFin)
            {
                moviendoAFin = false;
                sprite.flipX = false;
            }
        }
        else
        {
            transform.position = Vector3.MoveTowards(transform.position, posicionInicio, velocidad * Time.deltaTime);
            if (transform.position == posicionInicio)
            {
                moviendoAFin = true;
                sprite.flipX = true;
            }
        }
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.CompareTag("Player"))
        {
            collision.gameObject.GetComponent<ControlPlayer>().FinJuego();
        }
    }
}
