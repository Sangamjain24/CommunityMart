import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

function Home() {
    const [products, setProducts] = useState([])
    const navigate = useNavigate()

    // Page load hote hi backend se products mangwane ka logic
    useEffect(() => {
        fetch('http://localhost:8080/api/products')
            .then(response => response.json())
            .then(data => setProducts(data))
            .catch(error => console.error("Error fetching products:", error))
    }, [])

    const handleLogout = () => {
        alert("Logged out successfully!")
        navigate('/login')
    }

    return (
        <div style={{ padding: '20px', fontFamily: 'Arial' }}>
            {/* Navbar Section */}
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', backgroundColor: '#f8f9fa', padding: '10px 20px', borderRadius: '8px' }}>
                <h2 style={{ margin: 0 }}>🕉️ Religious Marketplace</h2>
                <button onClick={handleLogout} style={{ padding: '8px 15px', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
                    Logout
                </button>
            </div>

            <hr style={{ margin: '20px 0' }} />
            <h3>Featured Products</h3>

            {/* Products Grid Section */}
            <div style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', marginTop: '20px' }}>
                {products.length > 0 ? (
                    products.map((product) => (
                        <div key={product.id} style={{ border: '1px solid #ddd', padding: '15px', borderRadius: '8px', width: '220px', textAlign: 'center', boxShadow: '0 4px 8px rgba(0,0,0,0.1)' }}>
                            <h4 style={{ color: '#333' }}>{product.name}</h4>
                            <p style={{ fontSize: '14px', color: '#666' }}>{product.description}</p>
                            <h3 style={{ color: '#28a745', margin: '10px 0' }}>₹{product.price}</h3>
                            <button style={{ width: '100%', padding: '8px', backgroundColor: '#FF9933', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer', fontWeight: 'bold' }}>
                                Buy Now
                            </button>
                        </div>
                    ))
                ) : (
                    <p>No products available right now. Please check back later!</p>
                )}
            </div>
        </div>
    )
}

export default Home