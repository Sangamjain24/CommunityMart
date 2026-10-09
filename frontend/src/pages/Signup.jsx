import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'

function Signup() {
    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const navigate = useNavigate()

    const handleSignup = async (e) => {
        e.preventDefault()

        try {
            const response = await fetch('http://localhost:8080/api/users/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                // Default role hum BUYER bhej rahe hain
                body: JSON.stringify({ name: name, email: email, password: password, role: 'BUYER' })
            })

            if (response.ok) {
                alert("Registration Successful! Please login.")
                navigate('/login') // Register hote hi wapas login par bhej do
            } else {
                alert("Registration failed!")
            }

        } catch (error) {
            alert('Error connecting to backend!')
        }
    }

    return (
        <div style={{ textAlign: 'center', marginTop: '50px', fontFamily: 'Arial' }}>
            <h2>🙏 Religious Marketplace</h2>
            <h3>Create a New Account</h3>

            <form onSubmit={handleSignup} style={{ display: 'inline-block', textAlign: 'left', padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>

                <div style={{ marginBottom: '10px' }}>
                    <label>Full Name: </label><br />
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                        style={{ padding: '5px', width: '200px' }}
                    />
                </div>

                <div style={{ marginBottom: '10px' }}>
                    <label>Email ID: </label><br />
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        style={{ padding: '5px', width: '200px' }}
                    />
                </div>

                <div style={{ marginBottom: '15px' }}>
                    <label>Password: </label><br />
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{ padding: '5px', width: '200px' }}
                    />
                </div>

                <button type="submit" style={{ width: '100%', padding: '8px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
                    Sign Up
                </button>

                <p style={{ marginTop: '15px', fontSize: '14px', textAlign: 'center' }}>
                    Already have an account? <Link to="/login">Login here</Link>
                </p>
            </form>
        </div>
    )
}

export default Signup